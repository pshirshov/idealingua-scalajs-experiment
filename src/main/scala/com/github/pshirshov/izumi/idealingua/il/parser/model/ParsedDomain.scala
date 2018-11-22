package com.github.pshirshov.izumi.idealingua.il.parser.model

import com.github.pshirshov.izumi.idealingua.model.common.DomainId
import com.github.pshirshov.izumi.idealingua.model.il.ast.raw.IL

import scala.scalajs.js.annotation.JSExportAll

@JSExportAll
final case class ParsedDomain(
                               did: DomainId
                               , imports: Array[IL.Import]
                               , model: ParsedModel
                             )


