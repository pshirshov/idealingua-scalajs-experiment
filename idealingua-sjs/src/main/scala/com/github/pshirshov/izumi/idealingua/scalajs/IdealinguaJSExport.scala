package com.github.pshirshov.izumi.idealingua.scalajs

import com.github.pshirshov.izumi.idealingua.il.loader
import com.github.pshirshov.izumi.idealingua.il.loader.{ModelLoaderContextImpl, ModelResolver}
import com.github.pshirshov.izumi.idealingua.il.renderer.{IDLRenderer, IDLRenderingOptions}
import com.github.pshirshov.izumi.idealingua.model.loader.LoadedDomain
import com.github.pshirshov.izumi.idealingua.model.publishing.manifests.{CSharpBuildManifest, GoLangBuildManifest, ScalaBuildManifest, TypeScriptBuildManifest}
import com.github.pshirshov.izumi.idealingua.model.typespace.verification.VerificationRule
import com.github.pshirshov.izumi.idealingua.scalajs.codecs.Better._
import com.github.pshirshov.izumi.idealingua.scalajs.model.{CompilationResult, LoadedModelsDTO}
import com.github.pshirshov.izumi.idealingua.translator._

import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Idealingua")
object IdealinguaJSExport extends IdealinguaJSFacade {

  import com.github.pshirshov.izumi.idealingua.scalajs.codecs.Codecs._

  @JSExport
  def compilePseudoFS(fs: js.Dictionary[String], languageId: String, providedRuntime: js.Dictionary[js.Any], rawManifest: js.Dictionary[js.Any], extensions: js.Array[String]): js.Object = {
    val language = IDLLanguage.parse(languageId)
    val rules = TypespaceCompilerBaseFacade.descriptor(language).rules
    val resolved = load(fs.toMap, rules)
    val models = resolved.all

    val rt = if (providedRuntime.isEmpty) {
      None
    } else {
      Some(read[ProvidedRuntime](JSON.stringify(providedRuntime)))
    }

    val manifest = language match {
      case IDLLanguage.Scala => readManifest(rawManifest, ScalaBuildManifest.default)
      case IDLLanguage.Go => readManifest(rawManifest, GoLangBuildManifest.default)
      case IDLLanguage.Typescript => readManifest(rawManifest, TypeScriptBuildManifest.default)
      case IDLLanguage.CSharp => readManifest(rawManifest, CSharpBuildManifest.default)
    }

    val translatorExtensions: Seq[TranslatorExtension] = getExt(language, extensions.toList)

    val success = models.collect {
      case s: LoadedDomain.Success => s
    }

    val failures = models.collect {
      case f: LoadedDomain.Failure => f
    }

    val result: CompilationResult = if (failures.nonEmpty) {
      CompilationResult.FailedToLoad(failures)
    } else {
      val options = UntypedCompilerOptions(language, translatorExtensions, manifest, withBundledRuntime = false, rt)
      val modules = new TypespaceCompilerBaseFacade(options).compile(success).modules
      val asMap = modules
        .map {
          m =>
            ((m.id.path :+ m.id.name).mkString("/"), m.content)
        }
        .toMap
      CompilationResult.Success(asMap, success.flatMap(_.warnings))
    }

    val asJson: String = write(result)
    JSON.parse(asJson).asInstanceOf[js.Object]
  }

  @JSExport
  def parsePseudoFS(fs: js.Dictionary[String]): js.Object = {
    val resolved = load(fs.toMap, TypespaceCompilerBaseFacade.descriptors.flatMap(_.rules))
    val asDTO = LoadedModelsDTO(resolved)
    val asJson = write(asDTO)
    JSON.parse(asJson).asInstanceOf[js.Object]
  }

  @JSExport
  def prettyPrintDomains(fs: js.Dictionary[String]): js.Object = {
    val resolved = load(fs.toMap, TypespaceCompilerBaseFacade.descriptors.flatMap(_.rules))

    val printed = resolved.successful.map {
      d =>
        d.path -> new IDLRenderer(d.typespace.domain, IDLRenderingOptions(expandIncludes = false)).render()
    }

    val asJson = write(printed)
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

  private def readManifest[R : Reader](rawManifest: js.Dictionary[js.Any], default: R): R = {
    if (rawManifest.isEmpty) {
      default
    } else {
      val mfJson = JSON.stringify(rawManifest)
      read[R](mfJson)
    }
  }
}
