package com.github.pshirshov.izumi.idealingua.scalajs.codecs

import com.github.pshirshov.izumi.idealingua.model.loader.{LoadedDomain, ModelParsingResult, RefResolverIssue, TyperIssue}
import upickle.default.{macroRW, ReadWriter => RW, _}


trait CodecsRefResolverIssue extends Types with CodecsTyperIssue with CodecsTypespaceVerificationIssue {
  implicit def rw_ModelParsingResult_Failure: RW[ModelParsingResult.Failure] = macroRW

  implicit def rw_LoadedDomain_Failure: RW[LoadedDomain.Failure] = macroRW

  implicit def rw_LoadedDomain_Failure_TyperFailed: RW[LoadedDomain.TyperFailed] = macroRW

  implicit def rw_LoadedDomain_Failure_ParsingFailed: RW[LoadedDomain.ParsingFailed] = macroRW

  implicit def rw_LoadedDomain_Failure_ResolutionFailed: RW[LoadedDomain.ResolutionFailed] = macroRW

  implicit def rw_LoadedDomain_Failure_VerificationFailed: RW[LoadedDomain.VerificationFailed] = macroRW


  case class RefResolverIssueExtendedIssue(issue: RefResolverIssue, repr: String)

  object RefResolverIssueExtendedIssue {
    implicit def wExtendedIssue: Writer[RefResolverIssueExtendedIssue] = {
      implicit def justIssue: RW[RefResolverIssue] = macroRW[RefResolverIssue]

      macroRW
    }
  }

  implicit def r_RefResolverIssue: Reader[RefResolverIssue] = macroR


  implicit def w_RefResolverIssue: Writer[RefResolverIssue] = {
    writer[RefResolverIssueExtendedIssue].comap[RefResolverIssue](i => RefResolverIssueExtendedIssue(i, i.toString))
  }

  implicit def rw_RefResolverIssue_MissingImport: RW[RefResolverIssue.MissingImport] = macroRW

  implicit def rw_RefResolverIssue_MissingInclusion: RW[RefResolverIssue.MissingInclusion] = macroRW

  implicit def rw_RefResolverIssue_UnparseableInclusion: RW[RefResolverIssue.UnparseableInclusion] = macroRW

  implicit def rw_RefResolverIssue_UnresolvableImport: RW[RefResolverIssue.UnresolvableImport] = macroRW

  implicit def rw_RefResolverIssue_TyperException: RW[RefResolverIssue.DuplicatedDomains] = macroRW
}
