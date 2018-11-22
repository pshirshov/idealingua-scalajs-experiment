package com.github.pshirshov.izumi.idealingua.model.il.ast.raw

import com.github.pshirshov.izumi.idealingua.model.common.DomainId
import com.github.pshirshov.izumi.idealingua.model.il.ast.raw.RawTypeDef.NewType

import scala.scalajs.js.annotation.JSExportAll

object IL {

  @JSExportAll
  final case class ImportedId(name: String, as: Option[String]) {
    def importedName: String = as.getOrElse(name)
  }

  @JSExportAll
  final case class Import(id: DomainId, identifiers: Set[ImportedId])


  sealed trait Val

  @JSExportAll
  final case class ILImport(domain: DomainId, id: ImportedId) extends Val

  @JSExportAll
  final case class ILInclude(i: String) extends Val

  @JSExportAll
  final case class ILDef(v: IdentifiedRawTypeDef) extends Val

  @JSExportAll
  final case class ILNewtype(v: NewType) extends Val

  @JSExportAll
  final case class ILService(v: Service) extends Val

  @JSExportAll
  final case class ILStreams(v: Streams) extends Val

  @JSExportAll
  final case class ILBuzzer(v: Buzzer) extends Val

  @JSExportAll
  final case class ILConst(v: Constants) extends Val

}
