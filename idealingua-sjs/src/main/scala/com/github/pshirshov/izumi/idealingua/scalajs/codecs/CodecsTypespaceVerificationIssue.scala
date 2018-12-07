package com.github.pshirshov.izumi.idealingua.scalajs.codecs

import com.github.pshirshov.izumi.idealingua.model.loader.{LoadedDomain, ModelParsingResult}
import com.github.pshirshov.izumi.idealingua.model.problems._
import com.github.pshirshov.izumi.idealingua.model.typespace.verification.MissingDependency
import com.github.pshirshov.izumi.idealingua.scalajs.codecs.Better.{macroRW, ReadWriter => RW}

trait CodecsTypespaceVerificationIssue extends Types {
  implicit def rwIDLDiagnostics: RW[IDLDiagnostics] = macroRW


  implicit def rwIDLError: RW[IDLError] = macroRW


  implicit def rwIDLWarning: RW[IDLWarning] = macroRW

  implicit def rwTypespaceWarning: RW[TypespaceWarning] = macroRW

  implicit def rwTypespaceWarning_Message: RW[TypespaceWarning.Message] = macroRW


  implicit def rwTyperError: RW[TyperError] = macroRW

  implicit def rwTyperError_TyperException: RW[TyperError.TyperException] = macroRW


  implicit def rwLoadedDomain_Failure: RW[LoadedDomain.Failure] = macroRW

  implicit def rwLoadedDomain_Failure_DiagnosableFailure: RW[LoadedDomain.DiagnosableFailure] = macroRW

  implicit def rwLoadedDomain_Failure_ParsingFailed: RW[LoadedDomain.ParsingFailed] = macroRW

  implicit def rwLoadedDomain_Failure_TyperFailed: RW[LoadedDomain.TyperFailed] = macroRW

  implicit def rwLoadedDomain_Failure_ResolutionFailed: RW[LoadedDomain.ResolutionFailed] = macroRW

  implicit def rwLoadedDomain_Failure_VerificationFailed: RW[LoadedDomain.VerificationFailed] = macroRW

  implicit def rwLoadedDomain_Failure_PostVerificationFailure: RW[LoadedDomain.PostVerificationFailure] = macroRW


  implicit def rw_ModelParsingResult_Failure: RW[ModelParsingResult.Failure] = macroRW

  implicit def rwPostError: RW[PostError] = macroRW

  implicit def rwPostError_DuplicatedDomains: RW[PostError.DuplicatedDomains] = macroRW


  implicit def rw_RefResolverIssue: RW[RefResolverIssue] = macroRW

  implicit def rw_RefResolverIssue_MissingImport: RW[RefResolverIssue.MissingImport] = macroRW

  implicit def rw_RefResolverIssue_MissingInclusion: RW[RefResolverIssue.MissingInclusion] = macroRW

  implicit def rw_RefResolverIssue_UnparseableInclusion: RW[RefResolverIssue.UnparseableInclusion] = macroRW

  implicit def rw_RefResolverIssue_UnresolvableImport: RW[RefResolverIssue.UnresolvableImport] = macroRW

  implicit def rw_RefResolverIssue_TyperException: RW[RefResolverIssue.DuplicatedDomainsDuringLookup] = macroRW


  implicit def rw_TypespaceError: RW[TypespaceError] = macroRW

  implicit def rw38: RW[TypespaceError.AmbigiousAdtMember] = macroRW

  implicit def rw39: RW[TypespaceError.CyclicInheritance] = macroRW

  implicit def rw40: RW[TypespaceError.CyclicUsage] = macroRW

  implicit def rw41: RW[TypespaceError.DuplicateAdtElements] = macroRW

  implicit def rw42: RW[TypespaceError.DuplicateEnumElements] = macroRW

  implicit def rw43: RW[TypespaceError.MissingDependencies] = macroRW

  implicit def rw44: RW[TypespaceError.NoncapitalizedTypename] = macroRW

  implicit def rw45: RW[TypespaceError.PrimitiveAdtMember] = macroRW

  implicit def rw46: RW[TypespaceError.ReservedTypenamePrefix] = macroRW

  implicit def rw47: RW[TypespaceError.ShortName] = macroRW

  implicit def rw48: RW[TypespaceError.VerificationException] = macroRW


  implicit def rw30: RW[MissingDependency] = macroRW

  implicit def rw31: RW[MissingDependency.DepServiceParameter] = macroRW

  implicit def rw32: RW[MissingDependency.DepParameter] = macroRW

  implicit def rw33: RW[MissingDependency.DepAlias] = macroRW

  implicit def rw34: RW[MissingDependency.DepField] = macroRW

  implicit def rw35: RW[MissingDependency.DepInterface] = macroRW

  implicit def rw36: RW[MissingDependency.DepPrimitiveField] = macroRW

}
