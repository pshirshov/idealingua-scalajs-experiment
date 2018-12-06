package com.github.pshirshov.izumi.idealingua.scalajs.codecs

import com.github.pshirshov.izumi.idealingua.model.typespace.{TypespaceData, _}
import com.github.pshirshov.izumi.idealingua.scalajs.model._
import upickle.default.{macroRW, ReadWriter => RW, _}


object Codecs extends MainModelCodec with Consts with CodecsRefResolverIssue {
  implicit def rw55: RW[TypespaceDTO] = macroRW

  implicit def rw53: RW[LoadedModelsDTO] = macroRW


  implicit def rw250: RW[CompilationResult] = macroRW

  implicit def rw251: RW[CompilationResult.FailedToLoad] = macroRW

  implicit def rw252: RW[CompilationResult.Success] = macroRW


  implicit def rw_LoadedDomainDTO_Failure: RW[LoadedDomainDTO.Failure] = macroRW

  implicit def rw_LoadedDomainDTO_ParsingFailed: RW[LoadedDomainDTO.ParsingFailed] = macroRW

  implicit def rw_LoadedDomainDTO_VerificationFailed: RW[LoadedDomainDTO.VerificationFailed] = macroRW

  implicit def rw_LoadedDomainDTO_ResolutionFailed: RW[LoadedDomainDTO.ResolutionFailed] = macroRW

  implicit def rw_LoadedDomainDTO_TyperFailed: RW[LoadedDomainDTO.TyperFailed] = macroRW

  implicit def rw_LoadedDomainDTO_Success: RW[LoadedDomainDTO.Success] = macroRW

  implicit def rw_LoadedDomainDTO_LoadedDomainDTO: RW[LoadedDomainDTO] = macroRW

  implicit def rwTypeCollectionDTO: RW[TypeCollectionDTO] = macroRW

  implicit def rwTypeCollectionData: RW[TypeCollectionData] = readwriter[ujson.Value].bimap[TypeCollectionData](
    data => writeJs(TypeCollectionDTO(data)),
    json => read[TypeCollectionData](json),
  )

  implicit def rwTypespaceData: RW[TypespaceData] = readwriter[ujson.Value].bimap[TypespaceData](
    data => writeJs(TypespaceDTO(data)),
    json => read[TypespaceDTO](json),
  )
}
