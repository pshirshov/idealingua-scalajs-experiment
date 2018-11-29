package tutorial.webapp

import java.util.UUID

import com.github.pshirshov.izumi.idealingua.il.loader.{FilesystemEnumerator, ModelLoaderContextImpl}
import com.github.pshirshov.izumi.idealingua.il.parser.IDLParser
import com.github.pshirshov.izumi.idealingua.model.il.ast.raw.IL
import com.github.pshirshov.izumi.idealingua.model.loader.LoadedModels
import com.github.pshirshov.izumi.idealingua.model.parser.ParsedDomain
import fastparse.core.Parsed
import org.querki.jquery._
import upickle.default._

import scala.scalajs.js
import scala.scalajs.js.JSConverters._
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation._

@JSExportTopLevel("IDLP")
object HelloWorld {
  import Codecs._

  @JSExport
  def fsj(fs: js.Dictionary[String]): js.Dictionary[String] = {
    JSON.parse(write(new PseudoContext(fs.toMap).loader.load())).asInstanceOf[js.Dictionary[String]]
  }

  @JSExport
  def fs(fs: js.Dictionary[String]): LoadedModels = {
    new PseudoContext(fs.toMap).loader.load()
  }

  @JSExport
  def parse(domain: String): String = {
    IDLParser.parseDomain(domain).toString
  }

  @JSExport
  def parseModel(domain: String): ParsedDomain = {
    IDLParser.parseDomain(domain) match {
      case Parsed.Success(value, index) =>
        value

      case Parsed.Failure(lastParser, index, extra) =>
        throw new RuntimeException("bad state")
    }
  }

  @JSExport
  def parseDefinitions(domain: String): js.Array[IL.Val] = {
    js.Array(parseModel(domain).model.definitions: _*)
  }
}

class PseudoContext(fs: Map[String, String]) extends ModelLoaderContextImpl {
  override def enumerator: FilesystemEnumerator = new FilesystemEnumerator.Pseudo(fs)
}

@JSGlobal("IDLP")
@js.native
object IDLPExample extends js.Object {
  def fs(fs: js.Dictionary[String]): LoadedModels = js.native
  def fsj(fs: js.Dictionary[String]): js.Dictionary[String] = js.native

  def parse(domain: String): String = js.native
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

    $("""<button type="button">Parse</button>""")
      .click(() => parseDom())
      .appendTo($("#controls-outer"))

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

  def parseDom(): Unit = {
    val input = $("#input").text()
    $("#output").text(IDLPExample.parse(input))
  }

  def parseFsJson(): Unit = {
    val input = $("#input").text()
    val fs = JSON.parse(input).asInstanceOf[js.Dictionary[String]]
    $("#output").text(write(read[ujson.Value](JSON.stringify(IDLPExample.fsj(fs))), indent = 2))
  }
}
