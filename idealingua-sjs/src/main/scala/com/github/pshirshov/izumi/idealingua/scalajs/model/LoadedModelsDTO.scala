package com.github.pshirshov.izumi.idealingua.scalajs.model

import com.github.pshirshov.izumi.idealingua.model.loader.{LoadedDomain, LoadedModels}

// we need to copy this bcs typespace is not a dto
case class LoadedModelsDTO(loaded: Seq[LoadedDomainDTO])

object LoadedModelsDTO {
  def apply(models: LoadedModels): LoadedModelsDTO = {
    LoadedModelsDTO(models.all.map {
      case failure: LoadedDomain.Failure =>
        LoadedDomainDTO.Failure(failure)
      case LoadedDomain.Success(path, typespace, warnings) =>
        LoadedDomainDTO.Success(path, TypespaceDTO(typespace), warnings)
    })
  }
}
