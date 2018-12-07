package com.github.pshirshov.izumi.idealingua.scalajs.codecs

import com.github.pshirshov.izumi.idealingua.model.problems.IDLError
import upickle.core.Visitor

object Better extends upickle.AttributeTagged {

  override def taggedWrite[T, R](w: CaseW[T], tag: String, out: Visitor[_, R], v: T): R = {
    val s =
    if (v.isInstanceOf[IDLError]) {
      println((v, v.getClass))

      val ctx = out.asInstanceOf[Visitor[Any, R]].visitObject(w.length(v) + 1, -1)
      val keyVisitor = ctx.visitKey(-1)

      ctx.visitKeyValue(keyVisitor.visitString(tagName, -1))
      ctx.visitValue(out.visitString(objectTypeKeyWriteMap(tag), -1), -1)

      ctx.visitKeyValue("MESSAGE")
      ctx.visitKeyValue("REPR")

      w.writeToObject(ctx, v)
      val res = ctx.visitEnd(-1)
      res

    } else {
      super.taggedWrite[T, R](w, tag, out, v)

    }

  }
}
