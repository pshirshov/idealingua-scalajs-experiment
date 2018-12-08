package com.github.pshirshov.izumi.idealingua.scalajs.codecs

import com.github.pshirshov.izumi.idealingua.model.problems.{IDLError, IDLWarning}
import upickle.core.Visitor

object Better extends upickle.AttributeTagged {
  override def taggedWrite[T, R](w: CaseW[T], tag: String, out: Visitor[_, R], v: T): R = {
    v match {
      case error: IDLError =>
        withReprTaggedWriter[T, R](w, tag, out, v, error.toString)
      case warning: IDLWarning =>
        withReprTaggedWriter[T, R](w, tag, out, v, warning.toString)
      case o =>
        super.taggedWrite[T, R](w, tag, out, o)
    }
  }

  private def withReprTaggedWriter[T, R](w: CaseW[T], tag: String, out: Visitor[_,  R], v: T, repr: String): R = {
    val ctx = out.asInstanceOf[Visitor[Any, R]].visitObject(w.length(v) + 1, -1)
    val keyVisitor = ctx.visitKey(-1)

    ctx.visitKeyValue(keyVisitor.visitString(tagName, -1))
    ctx.visitValue(out.visitString(objectTypeKeyWriteMap(tag), -1), -1)

    ctx.visitKeyValue(keyVisitor.visitString("_repr_", -1))
    ctx.visitValue(out.visitString(objectTypeKeyWriteMap(repr), -1), -1)

    w.writeToObject(ctx, v)
    val res = ctx.visitEnd(-1)
    res
  }
}
