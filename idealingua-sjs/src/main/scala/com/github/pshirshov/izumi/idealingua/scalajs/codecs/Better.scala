package com.github.pshirshov.izumi.idealingua.scalajs.codecs

import com.github.pshirshov.izumi.idealingua.model.problems.IDLError
import upickle.core.Visitor

object Better extends upickle.AttributeTagged {

  override def taggedWrite[T, R](w: CaseW[T], tag: String, out: Visitor[_, R], v: T): R = {
    val s = super.taggedWrite[T, R](w, tag, out, v)
    if (v.isInstanceOf[IDLError]) {
      println((v, v.getClass))
    }
    s
  }
}
