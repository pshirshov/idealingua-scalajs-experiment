package com.github.pshirshov.izumi.idealingua.scalajs.model

sealed trait CompilationResult

object CompilationResult {

  final case class FailedToLoad(failure: LoadedDomainDTO.Failure) extends CompilationResult

  final case class Success(modules: Map[String, String]) extends CompilationResult

}
