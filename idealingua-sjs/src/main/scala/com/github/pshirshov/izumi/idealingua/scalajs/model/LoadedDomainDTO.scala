package com.github.pshirshov.izumi.idealingua.scalajs.model

import com.github.pshirshov.izumi.idealingua.model.common.DomainId
import com.github.pshirshov.izumi.idealingua.model.loader.{FSPath, LoadedDomain}
import com.github.pshirshov.izumi.idealingua.model.problems.{IDLDiagnostics, IDLWarning, RefResolverIssue}
import com.github.pshirshov.izumi.idealingua.model.typespace.TypespaceData

sealed trait LoadedDomainDTO

object LoadedDomainDTO {

  final case class Success(path: FSPath, typespace: TypespaceData, warnings: Vector[IDLWarning]) extends LoadedDomainDTO
  final case class Failure(failure: LoadedDomain.Failure) extends LoadedDomainDTO

}
