package com.github.pshirshov.izumi.idealingua.scalajs

import scala.scalajs.js
import scala.scalajs.js.Dictionary
import scala.scalajs.js.annotation.JSGlobal

@JSGlobal("Idealingua")
@js.native
object IdealinguaJSImport extends js.Object {
  this: IdealinguaJSFacade => // this doesn't really work but at least allows to cogen methods in idea

  def parsePseudoFS(fs: Dictionary[String]): js.Object = js.native

  def compilePseudoFS(fs: js.Dictionary[String], languageId: String, providedRuntime: js.Dictionary[js.Any], manifest: js.Dictionary[js.Any], extensions: js.Array[String]): js.Object = js.native
}
