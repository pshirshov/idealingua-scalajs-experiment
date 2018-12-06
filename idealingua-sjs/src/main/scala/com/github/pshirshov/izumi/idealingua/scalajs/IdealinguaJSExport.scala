package com.github.pshirshov.izumi.idealingua.scalajs

import com.github.pshirshov.izumi.idealingua.il.renderer.IDLRenderer
import com.github.pshirshov.izumi.idealingua.model.loader.LoadedDomain
import com.github.pshirshov.izumi.idealingua.model.publishing.BuildManifest
import com.github.pshirshov.izumi.idealingua.scalajs.model.{CompilationResult, LoadedDomainDTO, LoadedModelsDTO, PseudoContext}
import com.github.pshirshov.izumi.idealingua.translator.{TypespaceTranslatorFacade, _}
import upickle.default._

import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}


@JSExportTopLevel("Idealingua")
object IdealinguaJSExport extends IdealinguaJSFacade {

  import com.github.pshirshov.izumi.idealingua.scalajs.codecs.Codecs._

  @JSExport
  def compilePseudoFS(fs: js.Dictionary[String], languageId: String, providedRuntime: js.Dictionary[js.Any], manifest: js.Dictionary[js.Any], extensions: js.Array[String]): js.Object = {
    val models = new PseudoContext(fs.toMap).loader.load()

    val rt = if (providedRuntime.isEmpty) {
      None
    } else {
      Some(read[ProvidedRuntime](JSON.stringify(providedRuntime)))
    }

    val manifest: Option[BuildManifest] = None

    val language = IDLLanguage.parse(languageId)

    val translatorExtensions: Seq[TranslatorExtension] = getExt(language, extensions.toList)

    val result: Seq[CompilationResult] = models.loaded.map {
      case failure: LoadedDomain.Failure =>
        CompilationResult.FailedToLoad(LoadedDomainDTO.Failure(failure))

      case LoadedDomain.Success(path, typespace) =>
        val options = UntypedCompilerOptions(language, translatorExtensions, withRuntime = true, manifest, rt)
        val modules = new TypespaceTranslatorFacade(typespace).translate(options)
        CompilationResult.Success(modules.map {
          m =>
            (m.id.path.mkString("/"), m.content)
        }.toMap)
    }

    val asJson: String = write(result)
    JSON.parse(asJson).asInstanceOf[js.Object]
  }

  @JSExport
  def parsePseudoFS(fs: js.Dictionary[String]): js.Object = {
    val models = new PseudoContext(fs.toMap).loader.load()
    val asDTO = LoadedModelsDTO(models)
    val asJson = write(asDTO)
    JSON.parse(asJson).asInstanceOf[js.Object]
  }

  @JSExport
  def prettyPrintDomains(fs: js.Dictionary[String]): js.Object = {
    val models = new PseudoContext(fs.toMap).loader.load()
    val printed = models.successful.map {
      d =>
        d.path -> new IDLRenderer(d.typespace.domain).render()
    }

    val asJson = write(printed)
    JSON.parse(asJson).asInstanceOf[js.Object]
  }


  private def getExt(lang: IDLLanguage, filter: List[String]): Seq[TranslatorExtension] = {
    val all = TypespaceTranslatorFacade.extensions(lang)
    val negative = filter.filter(_.startsWith("-")).map(_.substring(1)).map(ExtensionId).toSet
    all.filterNot(e => negative.contains(e.id))
  }

}
