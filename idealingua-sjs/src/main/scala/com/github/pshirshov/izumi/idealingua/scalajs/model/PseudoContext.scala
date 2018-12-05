package com.github.pshirshov.izumi.idealingua.scalajs.model

import com.github.pshirshov.izumi.idealingua.il.loader.{FilesystemEnumerator, ModelLoaderContextImpl}

class PseudoContext(fs: Map[String, String]) extends ModelLoaderContextImpl {
  override def enumerator: FilesystemEnumerator = new FilesystemEnumerator.Pseudo(fs)
}
