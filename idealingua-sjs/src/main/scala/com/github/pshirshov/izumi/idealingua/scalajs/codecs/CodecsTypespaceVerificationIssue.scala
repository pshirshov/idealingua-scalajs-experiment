package com.github.pshirshov.izumi.idealingua.scalajs.codecs

import com.github.pshirshov.izumi.idealingua.model.typespace.{MissingDependency, TypespaceVerificationIssue}
import upickle.default.{macroRW, ReadWriter => RW, _}

trait CodecsTypespaceVerificationIssue extends Types {
  implicit def rIssue: Reader[TypespaceVerificationIssue] = macroR

  case class ExtendedIssue(issue: TypespaceVerificationIssue, repr: String)

  object ExtendedIssue {
    implicit def wExtendedIssue: Writer[ExtendedIssue] = {
      implicit def justIssue: RW[TypespaceVerificationIssue] = macroRW[TypespaceVerificationIssue]

      macroRW
    }
  }


  implicit def rw37: Writer[TypespaceVerificationIssue] = {
    writer[ExtendedIssue].comap[TypespaceVerificationIssue](i => ExtendedIssue(i, i.toString))
  }

  implicit def rw38: RW[TypespaceVerificationIssue.AmbigiousAdtMember] = macroRW

  implicit def rw39: RW[TypespaceVerificationIssue.CyclicInheritance] = macroRW

  implicit def rw40: RW[TypespaceVerificationIssue.CyclicUsage] = macroRW

  implicit def rw41: RW[TypespaceVerificationIssue.DuplicateAdtElements] = macroRW

  implicit def rw42: RW[TypespaceVerificationIssue.DuplicateEnumElements] = macroRW

  implicit def rw43: RW[TypespaceVerificationIssue.MissingDependencies] = macroRW

  implicit def rw44: RW[TypespaceVerificationIssue.NoncapitalizedTypename] = macroRW

  implicit def rw45: RW[TypespaceVerificationIssue.PrimitiveAdtMember] = macroRW

  implicit def rw46: RW[TypespaceVerificationIssue.ReservedTypenamePrefix] = macroRW

  implicit def rw47: RW[TypespaceVerificationIssue.ShortName] = macroRW

  implicit def rw48: RW[TypespaceVerificationIssue.VerificationException] = macroRW


  implicit def rw30: RW[MissingDependency] = macroRW

  implicit def rw31: RW[MissingDependency.DepServiceParameter] = macroRW

  implicit def rw32: RW[MissingDependency.DepParameter] = macroRW

  implicit def rw33: RW[MissingDependency.DepAlias] = macroRW

  implicit def rw34: RW[MissingDependency.DepField] = macroRW

  implicit def rw35: RW[MissingDependency.DepInterface] = macroRW

  implicit def rw36: RW[MissingDependency.DepPrimitiveField] = macroRW

}
