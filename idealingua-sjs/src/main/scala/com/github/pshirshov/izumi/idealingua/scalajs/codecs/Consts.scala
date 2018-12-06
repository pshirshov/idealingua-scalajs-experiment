package com.github.pshirshov.izumi.idealingua.scalajs.codecs

import com.github.pshirshov.izumi.idealingua.model.il.ast.typed
import upickle.default.{macroRW, ReadWriter => RW, _}


trait Consts extends Identifiers {
  implicit def rwTypedValue: RW[typed.ConstValue] = macroRW

  implicit def rwTypedValue0: RW[typed.ConstValue.Typed] = macroRW

  implicit def rwTypedValue1: RW[typed.ConstValue.CMap] = macroRW

  implicit def rwTypedValue2: RW[typed.ConstValue.CTyped] = macroRW

  implicit def rwTypedValue3: RW[typed.ConstValue.CTypedList] = macroRW

  implicit def rwTypedValue4: RW[typed.ConstValue.CTypedObject] = macroRW

  implicit def rwTypedValue5: RW[typed.ConstValue.CList] = macroRW

  implicit def rwTypedValue6: RW[typed.ConstValue.CInt] = macroRW

  implicit def rwTypedValue7: RW[typed.ConstValue.CLong] = macroRW

  implicit def rwTypedValue8: RW[typed.ConstValue.CBool] = macroRW

  implicit def rwTypedValue9: RW[typed.ConstValue.CFloat] = macroRW

  implicit def rwTypedValue10: RW[typed.ConstValue.CString] = macroRW
}
