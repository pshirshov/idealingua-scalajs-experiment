package com.github.pshirshov.izumi.idealingua.scalajs.model

import com.github.pshirshov.izumi.idealingua.model.loader.LoadedDomain

sealed trait CompilationResult

object CompilationResult {

  final case class FailedToLoad(failure: LoadedDomain.Failure) extends CompilationResult

  final case class Success(modules: Map[String, String]) extends CompilationResult

}
