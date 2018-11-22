package com.github.pshirshov.izumi.idealingua.il.parser.model

import com.github.pshirshov.izumi.idealingua.model.il.ast.raw.IL

import scala.scalajs.js.annotation.JSExportAll


@JSExportAll
final case class ParsedModel private(definitions: Array[IL.Val], includes: Array[String])


object ParsedModel {
  def apply(definitions: Seq[IL.Val]): ParsedModel = {
    val includes = definitions.collect({ case d: IL.ILInclude => d.i })

    new ParsedModel(definitions.filterNot(_.isInstanceOf[IL.ILInclude]).toArray, includes.toArray)
  }
}
