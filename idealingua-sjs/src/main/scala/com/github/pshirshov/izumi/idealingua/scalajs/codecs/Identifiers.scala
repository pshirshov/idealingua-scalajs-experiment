package com.github.pshirshov.izumi.idealingua.scalajs.codecs

import com.github.pshirshov.izumi.idealingua.model.common.TypeId._
import com.github.pshirshov.izumi.idealingua.model.common._
import Better.{macroRW, ReadWriter => RW, _}

trait Identifiers {
  implicit def rw15: RW[TypePath] = macroRW

  implicit def rw4: RW[DomainId] = macroRW

  implicit def rw5: RW[TypeId] = macroRW

  implicit def rw6: RW[AdtId] = macroRW

  implicit def rw7: RW[Builtin] = macroRW

  implicit def rw8: RW[AliasId] = macroRW

  implicit def rw9: RW[ScalarId] = macroRW

  implicit def rw10: RW[StructureId] = macroRW

  implicit def rw11: RW[IdentifierId] = macroRW

  implicit def rw12: RW[InterfaceId] = macroRW

  implicit def rw12_1: RW[BuzzerId] = macroRW

  implicit def rw12_2: RW[StreamsId] = macroRW

  implicit def rw13: RW[DTOId] = macroRW

  implicit def rw14: RW[EnumId] = macroRW


  implicit def rw16: RW[ServiceId] = macroRW

  implicit def rw17: RW[Generic] = macroRW

  implicit def rw18: RW[Primitive] = macroRW

  implicit def rw19: RW[PrimitiveId] = macroRW

  implicit def rw26: RW[Generic.TList] = macroRW

  implicit def rw27: RW[Generic.TMap] = macroRW

  implicit def rw28: RW[Generic.TOption] = macroRW

  implicit def rw29: RW[Generic.TSet] = macroRW

}
