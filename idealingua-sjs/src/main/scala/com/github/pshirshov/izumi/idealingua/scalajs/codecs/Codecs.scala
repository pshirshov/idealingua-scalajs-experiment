package com.github.pshirshov.izumi.idealingua.scalajs.codecs

import com.github.pshirshov.izumi.idealingua.model.typespace.{TypespaceData, _}
import com.github.pshirshov.izumi.idealingua.scalajs.model._
import Better.{macroRW, ReadWriter => RW, _}


object Codecs extends MainModelCodec with Consts with CodecsTypespaceVerificationIssue {
  implicit def rw55: RW[TypespaceDTO] = macroRW

  implicit def rwLoadedModelsDTO: RW[LoadedModelsDTO] = macroRW

  implicit def rwLoadedDomainDTO: RW[LoadedDomainDTO] = macroRW

  implicit def rwLoadedDomainDTO_Success: RW[LoadedDomainDTO.Success] = macroRW

  implicit def rwLoadedDomainDTO_Failure: RW[LoadedDomainDTO.Failure] = macroRW


  implicit def rw250: RW[CompilationResult] = macroRW

  implicit def rw251: RW[CompilationResult.FailedToLoad] = macroRW

  implicit def rw252: RW[CompilationResult.Success] = macroRW


  implicit def rwTypeCollectionDTO: RW[TypeCollectionDTO] = macroRW

  implicit def rwTypeCollectionData: RW[TypeCollectionData] = readwriter[ujson.Value].bimap[TypeCollectionData](
    data => writeJs(TypeCollectionDTO(data)),
    json => read[TypeCollectionData](json)
  )

  implicit def rwTypespaceData: RW[TypespaceData] = readwriter[ujson.Value].bimap[TypespaceData](
    data => writeJs(TypespaceDTO(data)),
    json => read[TypespaceDTO](json),
  )



}


