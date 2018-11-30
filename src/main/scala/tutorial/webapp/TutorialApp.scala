package tutorial.webapp

import java.util.UUID

import org.querki.jquery._
import upickle.default._

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.annotation._
import scala.scalajs.js.{Dictionary, JSON}

trait IdealinguaJSFacade {
  def parsePseudoFS(fs: js.Dictionary[String]): js.Dictionary[String]
}

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



@JSGlobal("Idealingua")
@js.native
object IdealinguaJSImport extends js.Object {
  this: IdealinguaJSFacade => // this doesn't really work but at least allows to cogen methods in idea

  def parsePseudoFS(fs: Dictionary[String]): Dictionary[String] = js.native
}

object TutorialApp {

  def main(args: Array[String]): Unit = {
    $(() => setupUI())
  }

  val domain: String =
    """
      |domain idltest.enums
      |
      |enum ShortSyntaxEnum = Element1 | Element2
    """.stripMargin

  val example: Map[String, String] = Map("idltest/enums.domain" -> domain)

  def setupUI(): Unit = {
    $("""<div id="input-outer"/>""")
      .appendTo($("body"))

    $("""<div id="output-outer"/>""")
      .appendTo($("body"))

    $("""<div id="controls-outer"/>""")
      .appendTo($("body"))

    $("""<textarea id="input" rows="15" cols="100" />""")
      .text(JSON.stringify(example.toJSDictionary))
      .appendTo($("#input-outer"))

    $("""<textarea id="output" rows="15" cols="100" />""")
      .appendTo($("#output-outer"))

    $("""<button type="button">Parse as FS json</button>""")
      .click(() => parseFsJson())
      .appendTo($("#controls-outer"))


    $("""<button type="button">UUID</button>""")
      .click(() => uuid())
      .appendTo($("#controls-outer"))
  }

  def uuid(): Unit = {
    $("#output").text($("#output").text() + "\n" + UUID.randomUUID().toString)
  }

  def parseFsJson(): Unit = {
    val input = $("#input").text()
    val fs = JSON.parse(input).asInstanceOf[js.Dictionary[String]]
    val parsed = IdealinguaJSImport.parsePseudoFS(fs)
    val asJsonStr = JSON.stringify(parsed)
    val asJson = read[ujson.Value](asJsonStr)
    val idented = write(asJson, indent = 2)
    $("#output").text(idented)
  }
}
