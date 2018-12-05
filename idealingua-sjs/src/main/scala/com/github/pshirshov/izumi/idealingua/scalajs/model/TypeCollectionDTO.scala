package com.github.pshirshov.izumi.idealingua.scalajs.model

import com.github.pshirshov.izumi.idealingua.model.common.TypeId.{BuzzerId, DTOId, InterfaceId, ServiceId}
import com.github.pshirshov.izumi.idealingua.model.il.ast.typed.{Buzzer, Service, TypeDef}
import com.github.pshirshov.izumi.idealingua.model.typespace.TypeCollectionData

case class TypeCollectionDTO(
                              all: Seq[TypeDef],
                              structures: Seq[TypeDef.WithStructure],
                              interfaceEphemerals: Seq[TypeDef.DTO],
                              interfaceEphemeralIndex: Map[InterfaceId, TypeDef.DTO],
                              interfaceEphemeralsReversed: Map[DTOId, InterfaceId],
                              dtoEphemerals: Seq[TypeDef.Interface],
                              dtoEphemeralIndex: Map[DTOId, TypeDef.Interface],
                              services: Map[ServiceId, Service],
                              serviceEphemerals: Seq[TypeDef],
                              buzzers: Map[BuzzerId, Buzzer],
                              buzzerEphemerals: Seq[TypeDef],
                            ) extends TypeCollectionData

object TypeCollectionDTO {
  def apply(data: TypeCollectionData): TypeCollectionDTO = new TypeCollectionDTO(
    data.all,
    data.structures,
    data.interfaceEphemerals,
    data.interfaceEphemeralIndex,
    data.interfaceEphemeralsReversed,
    data.dtoEphemerals,
    data.dtoEphemeralIndex,
    data.services,
    data.serviceEphemerals,
    data.buzzers,
    data.buzzerEphemerals,
  )
}
