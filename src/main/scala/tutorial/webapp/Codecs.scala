package tutorial.webapp

import com.github.pshirshov.izumi.idealingua.model.common.TypeId._
import com.github.pshirshov.izumi.idealingua.model.common._
import com.github.pshirshov.izumi.idealingua.model.il.ast.typed._
import com.github.pshirshov.izumi.idealingua.model.loader.{FSPath, LoadedDomain, LoadedModels}
import com.github.pshirshov.izumi.idealingua.model.typespace.{Issue, MissingDependency, Typespace, TypespaceImpl}
import upickle.default.{macroRW, ReadWriter => RW, _}

object Codecs {
  implicit def rw1: RW[FSPath.Full] = macroRW
  implicit def rw2: RW[FSPath.Name] = macroRW
  implicit def rw3: RW[FSPath] = macroRW
  implicit def rw4: RW[DomainId] = macroRW
  implicit def rw5: RW[TypeId] = macroRW
  implicit def rw6: RW[AdtId] = macroRW
  implicit def rw7: RW[Builtin] = macroRW
  implicit def rw8: RW[AliasId] = macroRW
  implicit def rw9: RW[ScalarId] = macroRW
  implicit def rw10: RW[StructureId] = macroRW
  implicit def rw11: RW[IdentifierId] = macroRW
  implicit def rw12: RW[InterfaceId] = macroRW
  implicit def rw12_1: RW[BuzzerId] = macroRW
  implicit def rw12_2: RW[StreamsId] = macroRW
  implicit def rw13: RW[DTOId] = macroRW
  implicit def rw14: RW[EnumId] = macroRW
  implicit def rw15: RW[TypePath] = macroRW
  implicit def rw16: RW[ServiceId] = macroRW
  implicit def rw17: RW[Generic] = macroRW
  implicit def rw18: RW[Primitive] = macroRW
  implicit def rw19: RW[PrimitiveId] = macroRW
  implicit def rw20: RW[Field] = macroRW
  implicit def rw21: RW[AdtMember] = macroRW
  implicit def rw22: RW[IdField] = macroRW
  implicit def rw23: RW[IdField.Enum] = macroRW
  implicit def rw24: RW[IdField.PrimitiveField] = macroRW
  implicit def rw25: RW[IdField.SubId] = macroRW
  implicit def rw26: RW[Generic.TList] = macroRW
  implicit def rw27: RW[Generic.TMap] = macroRW
  implicit def rw28: RW[Generic.TOption] = macroRW
  implicit def rw29: RW[Generic.TSet] = macroRW

  implicit def rw30: RW[MissingDependency] = macroRW
  implicit def rw31: RW[MissingDependency.DepServiceParameter] = macroRW
  implicit def rw32: RW[MissingDependency.DepParameter] = macroRW
  implicit def rw33: RW[MissingDependency.DepAlias] = macroRW
  implicit def rw34: RW[MissingDependency.DepField] = macroRW
  implicit def rw35: RW[MissingDependency.DepInterface] = macroRW
  implicit def rw36: RW[MissingDependency.DepPrimitiveField] = macroRW


  implicit def rw37: RW[Issue] = macroRW
  implicit def rw38: RW[Issue.AmbigiousAdtMember] = macroRW
  implicit def rw39: RW[Issue.CyclicInheritance] = macroRW
  implicit def rw40: RW[Issue.CyclicUsage] = macroRW
  implicit def rw41: RW[Issue.DuplicateAdtElements] = macroRW
  implicit def rw42: RW[Issue.DuplicateEnumElements] = macroRW
  implicit def rw43: RW[Issue.MissingDependencies] = macroRW
  implicit def rw44: RW[Issue.NoncapitalizedTypename] = macroRW
  implicit def rw45: RW[Issue.PrimitiveAdtMember] = macroRW
  implicit def rw46: RW[Issue.ReservedTypenamePrefix] = macroRW
  implicit def rw47: RW[Issue.ShortName] = macroRW

  implicit def rw48: RW[LoadedDomain.Failure] = macroRW
  implicit def rw49: RW[LoadedDomain.ParsingFailed] = macroRW
  implicit def rw50: RW[LoadedDomain.TypingFailed] = macroRW
  implicit def rw51: RW[LoadedDomain.Success] = macroRW
  implicit def rw52: RW[LoadedDomain] = macroRW
  implicit def rw53: RW[LoadedModels] = macroRW

  implicit def rw56: RW[DomainDefinition] = macroRW

  implicit def rw100: RW[TypeDef] = macroRW
  implicit def rw101: RW[TypeDef.Adt] = macroRW
  implicit def rw102: RW[TypeDef.Alias] = macroRW
  implicit def rw103: RW[TypeDef.DTO] = macroRW
  implicit def rw104: RW[TypeDef.Enumeration] = macroRW
  implicit def rw105: RW[TypeDef.Identifier] = macroRW
  implicit def rw106: RW[TypeDef.Interface] = macroRW
  implicit def rw107: RW[TypeDef.WithStructure] = macroRW
  implicit def rw108: RW[Service] = macroRW
  implicit def rw109: RW[Buzzer] = macroRW
  implicit def rw110: RW[Streams] = macroRW
  implicit def rw111: RW[NodeMeta] = macroRW
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

  implicit def rw119: RW[Anno] =  readwriter[ujson.Value].bimap[Anno](
    x => ujson.Null,
    json => null
  )

  implicit def rw54: RW[Typespace] = readwriter[ujson.Value].bimap[Typespace](
    x => write(x.asInstanceOf[TypespaceImpl]),
    json => read[TypespaceImpl](json)
  )
  implicit def rw55: RW[TypespaceImpl] = macroRW


  implicit def rw120: RW[Super] = macroRW
}
