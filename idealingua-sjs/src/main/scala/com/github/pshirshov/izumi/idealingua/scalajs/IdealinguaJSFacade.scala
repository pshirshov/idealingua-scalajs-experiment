package com.github.pshirshov.izumi.idealingua.scalajs

import scala.scalajs.js

trait IdealinguaJSFacade {
  def parsePseudoFS(fs: js.Dictionary[String]): js.Object

  def compilePseudoFS(fs: js.Dictionary[String], languageId: String, providedRuntime: js.Dictionary[js.Any], manifest: js.Dictionary[js.Any], extensions: js.Array[String]): js.Object
}
