package com.github.pshirshov.izumi.idealingua.scalajs.codecs

import com.github.pshirshov.izumi.idealingua.model.il.ast.InputPosition
import com.github.pshirshov.izumi.idealingua.model.il.ast.typed._
import com.github.pshirshov.izumi.idealingua.model.loader.FSPath
import upickle.default.{macroRW, ReadWriter => RW, _}


trait Types extends Consts {
  implicit def rw1: RW[FSPath.Full] = macroRW

  implicit def rw2: RW[FSPath.Name] = macroRW

  implicit def rw3: RW[FSPath] = macroRW

  implicit def rw21: RW[AdtMember] = macroRW

  implicit def rw111: RW[NodeMeta] = macroRW

  implicit def rwAnno: RW[Anno] = macroRW

  implicit def rwInputPosition: RW[InputPosition] = macroRW

  implicit def rwInputPositionDefined: RW[InputPosition.Defined] = macroRW

  implicit def rwInputPositionUndefined: RW[InputPosition.Undefined.type] = macroRW

  implicit def rw20: RW[Field] = macroRW

  implicit def rw22: RW[IdField] = macroRW

  implicit def rw23: RW[IdField.Enum] = macroRW

  implicit def rw24: RW[IdField.PrimitiveField] = macroRW

  implicit def rw25: RW[IdField.SubId] = macroRW

}
