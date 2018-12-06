package com.github.pshirshov.izumi.idealingua.scalajs.codecs

import com.github.pshirshov.izumi.idealingua.model.loader.TyperIssue
import upickle.default.{macroRW, ReadWriter => RW, _}

trait CodecsTyperIssue extends Identifiers {

  case class TyperExtendedIssue(issue: TyperIssue, repr: String)

  object TyperExtendedIssue {
    implicit def wExtendedIssue: Writer[TyperExtendedIssue] = {
      implicit def justIssue: RW[TyperIssue] = macroRW[TyperIssue]

      macroRW
    }
  }

  implicit def r_TyperIssue: Reader[TyperIssue] = macroR


  implicit def w_TyperIssue: Writer[TyperIssue] = {
    writer[TyperExtendedIssue].comap[TyperIssue](i => TyperExtendedIssue(i, i.toString))
  }

  implicit def rw_TyperIssue_TyperException: RW[TyperIssue.TyperException] = macroRW
}
