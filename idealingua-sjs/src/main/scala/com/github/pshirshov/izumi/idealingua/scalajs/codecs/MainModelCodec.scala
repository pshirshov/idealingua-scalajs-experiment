package com.github.pshirshov.izumi.idealingua.scalajs.codecs

import com.github.pshirshov.izumi.idealingua.model.common.StreamDirection
import com.github.pshirshov.izumi.idealingua.model.il.ast.raw.domains.{Import, ImportedId}
import com.github.pshirshov.izumi.idealingua.model.il.ast.typed._
import com.github.pshirshov.izumi.idealingua.model.output.{Module, ModuleId}
import com.github.pshirshov.izumi.idealingua.model.publishing.BuildManifest.ManifestDependency
import com.github.pshirshov.izumi.idealingua.model.publishing.{BuildManifest, ProjectVersion, Publisher}
import com.github.pshirshov.izumi.idealingua.model.publishing.manifests._
import com.github.pshirshov.izumi.idealingua.scalajs.codecs.Better.{macroRW, ReadWriter => RW}
import com.github.pshirshov.izumi.idealingua.translator.{IDLLanguage, ProvidedRuntime}


trait MainModelCodec extends Types {
  implicit def rwImportedId: RW[ImportedId] = macroRW

  implicit def rwImport: RW[Import] = macroRW

  implicit def rwDomainMetadata: RW[DomainMetadata] = macroRW

  implicit def rw56: RW[DomainDefinition] = macroRW

  implicit def rw100: RW[TypeDef] = macroRW

  implicit def rw101: RW[TypeDef.Adt] = macroRW

  implicit def rw102: RW[TypeDef.Alias] = macroRW

  implicit def rw103: RW[TypeDef.DTO] = macroRW

  implicit def rw104_1: RW[EnumMember] = macroRW

  implicit def rw104: RW[TypeDef.Enumeration] = macroRW

  implicit def rw105: RW[TypeDef.Identifier] = macroRW

  implicit def rw106: RW[TypeDef.Interface] = macroRW

  implicit def rw107: RW[TypeDef.WithStructure] = macroRW

  implicit def rw108: RW[Service] = macroRW

  implicit def rw109: RW[Buzzer] = macroRW

  implicit def rw110: RW[Streams] = macroRW


  implicit def rw112: RW[Structure] = macroRW

  implicit def rw113: RW[DefMethod] = macroRW

  implicit def rw114: RW[DefMethod.Output] = macroRW

  implicit def rw114_1: RW[DefMethod.Output.Algebraic] = macroRW

  implicit def rw114_2: RW[DefMethod.Output.Alternative] = macroRW

  implicit def rw114_3: RW[DefMethod.Output.NonAlternativeOutput] = macroRW

  implicit def rw114_4: RW[DefMethod.Output.Singular] = macroRW

  implicit def rw114_5: RW[DefMethod.Output.Struct] = macroRW

  implicit def rw114_6: RW[DefMethod.Output.Void] = macroRW

  implicit def rw114_7: RW[SimpleStructure] = macroRW

  implicit def rw115: RW[DefMethod.RPCMethod] = macroRW

  implicit def rw116: RW[DefMethod.Signature] = macroRW

  implicit def rw118: RW[TypedStream] = macroRW

  implicit def rw118_1: RW[TypedStream.Directed] = macroRW

  implicit def rw118_2: RW[StreamDirection] = macroRW


  implicit def rw120: RW[Super] = macroRW


  implicit def rw201: RW[IDLLanguage] = macroRW

  implicit def rw204: RW[ProvidedRuntime] = macroRW

  implicit def rw205: RW[Module] = macroRW

  implicit def rw206: RW[ModuleId] = macroRW

  implicit def rw211: RW[ManifestDependency] = macroRW

  implicit def rw212: RW[Publisher] = macroRW

  implicit def rw_TypeScriptProjectLayout: RW[TypeScriptProjectLayout] = macroRW

  implicit def rw_ScalaProjectLayout: RW[ScalaProjectLayout] = macroRW

  implicit def rw_GoProjectLayout: RW[GoProjectLayout] = macroRW

  implicit def rw_CSharpProjectLayout: RW[CSharpProjectLayout] = macroRW

  implicit def `rwBuildManifest.Common`: RW[BuildManifest.Common] = macroRW

  implicit def `rwBuildManifest.License`: RW[BuildManifest.License] = macroRW

  implicit def `rwBuildManifest.MFUrl`: RW[BuildManifest.MFUrl] = macroRW

  implicit def rwProjectVersion: RW[ProjectVersion] = macroRW

  implicit def rw214: RW[ScalaBuildManifest] = macroRW
  implicit def rw_SbtOptions: RW[SbtOptions] = macroRW
  implicit def rw_YarnOptions: RW[YarnOptions] = macroRW
  implicit def rw_GoRepositoryOptions: RW[GoRepositoryOptions] = macroRW
  implicit def rw_NugetOptions: RW[NugetOptions] = macroRW

  implicit def rw215: RW[TypeScriptBuildManifest] = macroRW

  implicit def rw217: RW[GoLangBuildManifest] = macroRW

  implicit def rw218: RW[CSharpBuildManifest] = macroRW


}
