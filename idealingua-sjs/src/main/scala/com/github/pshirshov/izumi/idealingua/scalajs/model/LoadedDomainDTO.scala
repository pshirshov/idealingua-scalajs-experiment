package com.github.pshirshov.izumi.idealingua.scalajs.model

import com.github.pshirshov.izumi.idealingua.model.common.DomainId
import com.github.pshirshov.izumi.idealingua.model.loader.{FSPath, LoadedDomain}
import com.github.pshirshov.izumi.idealingua.model.typespace.{Issue, TypespaceData}

sealed trait LoadedDomainDTO

object LoadedDomainDTO {

  sealed trait Failure extends LoadedDomainDTO

  object Failure {
    def apply(failure: LoadedDomain.Failure): Failure = {
      failure match {
        case LoadedDomain.ParsingFailed(path, message) =>
          LoadedDomainDTO.ParsingFailed(path, message)
        case LoadedDomain.TypingFailed(path, domain, issues) =>
          LoadedDomainDTO.TypingFailed(path, domain, issues)
      }
    }
  }

  final case class Success(path: FSPath, typespace: TypespaceData) extends LoadedDomainDTO

  final case class ParsingFailed(path: FSPath, message: String) extends Failure

  final case class TypingFailed(path: FSPath, domain: DomainId, issues: List[Issue]) extends Failure

}
