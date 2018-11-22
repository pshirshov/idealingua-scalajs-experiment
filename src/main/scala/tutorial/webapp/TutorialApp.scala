package tutorial.webapp

import com.github.pshirshov.izumi.idealingua.il.parser.IDLParser
import com.github.pshirshov.izumi.idealingua.il.parser.model.ParsedDomain
import com.github.pshirshov.izumi.idealingua.model.il.ast.raw.IL
import fastparse.core.Parsed
import org.querki.jquery._

import scala.scalajs.js.annotation._
import scala.scalajs.js

@JSExportTopLevel("IDLP")
object HelloWorld {
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
    js.Array(parseModel(domain).model.definitions :_* )
  }

}

@JSGlobal("IDLP")
@js.native
object IDLPExample extends js.Object {
  def parse(domain: String): String = js.native
}

object TutorialApp {
  def main(args: Array[String]): Unit = {
    $(() => setupUI())
  }

  val example =
    """domain idltest.enums
      |
      |enum ShortSyntaxEnum = Element1 | Element2
      |
      |
      |data SomeGenerics {
      |  test: map[TestEnum, TestEnum]
      |}
      |
        """.stripMargin

  def setupUI(): Unit = {
    $("""<div id="input-outer"/>""")
      .appendTo($("body"))

    $("""<div id="output-outer"/>""")
      .appendTo($("body"))

    $("""<div id="controls-outer"/>""")
      .appendTo($("body"))

    $("""<textarea id="input" rows="25" cols="100" />""")
      .text(example)
      .appendTo($("#input-outer"))

    $("""<textarea id="output" rows="25" cols="100" />""")
      .appendTo($("#output-outer"))

    $("""<button type="button">Parse</button>""")
      .click(() => addClickedMessage())
      .appendTo($("#controls-outer"))
  }

  def addClickedMessage(): Unit = {
    val input = $("#input").text()
    $("#output").text(IDLPExample.parse(input))
  }
}
