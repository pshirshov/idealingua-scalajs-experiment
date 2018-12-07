package com.github.pshirshov.izumi.idealingua.scalajs

import com.github.pshirshov.izumi.idealingua.il.loader
import com.github.pshirshov.izumi.idealingua.il.loader.{ModelLoaderContextImpl, ModelResolver}
import com.github.pshirshov.izumi.idealingua.il.renderer.{IDLRenderer, IDLRenderingOptions}
import com.github.pshirshov.izumi.idealingua.model.loader.LoadedDomain
import com.github.pshirshov.izumi.idealingua.model.publishing.BuildManifest
import com.github.pshirshov.izumi.idealingua.model.typespace.verification.VerificationRule
import com.github.pshirshov.izumi.idealingua.scalajs.codecs.Better
import com.github.pshirshov.izumi.idealingua.scalajs.model.{CompilationResult, LoadedModelsDTO}
import com.github.pshirshov.izumi.idealingua.translator._

import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Idealingua")
object IdealinguaJSExport extends IdealinguaJSFacade {

  import com.github.pshirshov.izumi.idealingua.scalajs.codecs.Codecs._
  import Better._

  @JSExport
  def compilePseudoFS(fs: js.Dictionary[String], languageId: String, providedRuntime: js.Dictionary[js.Any], manifest: js.Dictionary[js.Any], extensions: js.Array[String]): js.Object = {
    val language = IDLLanguage.parse(languageId)
    val rules = TypespaceCompilerBaseFacade.descriptor(language).rules
    val resolved = load(fs.toMap, rules)
    val models = resolved.all

    val rt = if (providedRuntime.isEmpty) {
      None
    } else {
      Some(read[ProvidedRuntime](JSON.stringify(providedRuntime)))
    }

    val manifest: Option[BuildManifest] = None


    val translatorExtensions: Seq[TranslatorExtension] = getExt(language, extensions.toList)

    val result: Seq[CompilationResult] = models.map {
      case failure: LoadedDomain.Failure =>
        CompilationResult.FailedToLoad(failure)

      case LoadedDomain.Success(path, typespace, warnings) =>
        val options = UntypedCompilerOptions(language, translatorExtensions, withRuntime = true, manifest, rt)
        val modules = new TypespaceCompilerBaseFacade(typespace, options).compile()
        CompilationResult.Success(modules.map {
          m =>
            (m.id.path.mkString("/"), m.content)
        }.toMap)
    }

    val asJson: String = write(result)
    println(asJson)
    JSON.parse(asJson).asInstanceOf[js.Object]
  }

  @JSExport
  def parsePseudoFS(fs: js.Dictionary[String]): js.Object = {
    val resolved = load(fs.toMap, TypespaceCompilerBaseFacade.descriptors.flatMap(_.rules))
    val asDTO = LoadedModelsDTO(resolved)
    val asJson = Better.write(asDTO)
    JSON.parse(asJson).asInstanceOf[js.Object]
  }

  @JSExport
  def prettyPrintDomains(fs: js.Dictionary[String]): js.Object = {
    val resolved = load(fs.toMap, TypespaceCompilerBaseFacade.descriptors.flatMap(_.rules))

    val printed = resolved.successful.map {
      d =>
        d.path -> new IDLRenderer(d.typespace.domain, IDLRenderingOptions(expandIncludes = false)).render()
    }

    val asJson = Better.write(printed)
    JSON.parse(asJson).asInstanceOf[js.Object]
  }

  private def load(fs: Map[String, String], rules: Seq[VerificationRule]) = {
    val context = new ModelLoaderContextImpl(_ => new loader.FilesystemEnumerator.Pseudo(fs))
    val unresolved = context.loader.load()
    val resolved = new ModelResolver(rules).resolve(unresolved)
    resolved
  }

  private def getExt(lang: IDLLanguage, filter: List[String]): Seq[TranslatorExtension] = {
    val all = TypespaceCompilerBaseFacade.descriptor(lang).defaultExtensions
    val negative = filter.filter(_.startsWith("-")).map(_.substring(1)).map(ExtensionId).toSet
    all.filterNot(e => negative.contains(e.id))
  }

}
