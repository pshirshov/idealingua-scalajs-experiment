package com.github.pshirshov.izumi.idealingua.scalajs.model

import com.github.pshirshov.izumi.idealingua.model.common.DomainId
import com.github.pshirshov.izumi.idealingua.model.loader.{FSPath, LoadedDomain, RefResolverIssue, TyperIssue}
import com.github.pshirshov.izumi.idealingua.model.typespace.{TypespaceData, TypespaceVerificationIssue}

sealed trait LoadedDomainDTO

object LoadedDomainDTO {

  final case class Success(path: FSPath, typespace: TypespaceData) extends LoadedDomainDTO

  sealed trait Failure extends LoadedDomainDTO

  object Failure {
    def apply(failure: LoadedDomain.Failure): Failure = {
      failure match {
        case LoadedDomain.ParsingFailed(path, message) =>
          LoadedDomainDTO.ParsingFailed(path, message)

        case LoadedDomain.TyperFailed(path, domain, issues) =>
          LoadedDomainDTO.TyperFailed(path, domain, issues)

        case LoadedDomain.VerificationFailed(path, domain, issues) =>
          LoadedDomainDTO.VerificationFailed(path, domain, issues)

        case LoadedDomain.ResolutionFailed(path, domain, issues) =>
          LoadedDomainDTO.ResolutionFailed(path, domain, issues)

      }
    }
  }


  final case class ParsingFailed(path: FSPath, message: String) extends Failure

  final case class VerificationFailed(path: FSPath, domain: DomainId, issues: List[TypespaceVerificationIssue]) extends Failure

  final case class ResolutionFailed(path: FSPath, domain: DomainId, issues: List[RefResolverIssue]) extends Failure

  final case class TyperFailed(path: FSPath, domain: DomainId, issues: List[TyperIssue]) extends Failure

}
