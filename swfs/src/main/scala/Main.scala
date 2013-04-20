package lltowm

import geotrellis.admin.{Reproject, Projections}
import geotrellis.process.{Server, Catalog}

import geotrellis.rest._

import geotrellis.feature.Point

object Main {
  def main(args:Array[String]):Unit = WebRunner.main(args)
}
