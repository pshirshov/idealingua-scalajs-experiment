package com.github.pshirshov.izumi.idealingua.scalajs

import upickle.default.write

import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("Idealingua")
object IdealinguaJSExport extends IdealinguaJSFacade {
  import Codecs._

  @JSExport
  def parsePseudoFS(fs: js.Dictionary[String]): js.Dictionary[String] = {
    val models = new PseudoContext(fs.toMap).loader.load()
    val asDTO = LoadedModelsDTO(models)
    val asJson = write(asDTO)
    JSON.parse(asJson).asInstanceOf[js.Dictionary[String]]
  }

}
