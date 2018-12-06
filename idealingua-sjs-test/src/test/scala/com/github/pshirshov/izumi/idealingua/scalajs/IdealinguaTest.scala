package com.github.pshirshov.izumi.idealingua.scalajs

import utest._
import org.querki.jquery._

import scala.scalajs.js
import scala.scalajs.js.JSON

object IdealinguaTest extends TestSuite {

  // Initialize App
  IdealinguaApp.setupUI()

  def tests = Tests {

    'ParseFs - {
      val button = $("button#parse-fs")
      assert(button.length == 1)
      button.click()

      val output = $("textarea#output")
      val parsed = JSON.parse(output.value().toString)
      val asDict = parsed.asInstanceOf[js.Dictionary[js.Object]]
      assert(asDict("loaded").isInstanceOf[js.Array[_]])

    }
  }
}
