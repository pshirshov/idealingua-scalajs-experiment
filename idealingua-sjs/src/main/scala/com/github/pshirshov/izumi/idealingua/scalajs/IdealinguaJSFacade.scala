package com.github.pshirshov.izumi.idealingua.scalajs

import scala.scalajs.js

trait IdealinguaJSFacade {
  def parsePseudoFS(fs: js.Dictionary[String]): js.Dictionary[String]
}
