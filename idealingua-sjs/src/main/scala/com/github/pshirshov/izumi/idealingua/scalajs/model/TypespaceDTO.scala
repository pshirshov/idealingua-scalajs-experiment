package com.github.pshirshov.izumi.idealingua.scalajs.model

import com.github.pshirshov.izumi.idealingua.model.il.ast.typed.DomainDefinition
import com.github.pshirshov.izumi.idealingua.model.typespace.{TypeCollectionData, TypespaceData}

case class TypespaceDTO(domain: DomainDefinition, types: TypeCollectionData) extends TypespaceData

object TypespaceDTO {
  def apply(data: TypespaceData): TypespaceDTO = new TypespaceDTO(data.domain, data.types)
}
