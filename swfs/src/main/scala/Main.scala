package nasa

import geotrellis.admin.{Reproject, Projections}
import geotrellis.process.{Server, Catalog}

import geotrellis.rest._

import geotrellis.feature.Point

object Main {
  def main(args:Array[String]) = WebRunner.main(args)
}

object RunServer {
  def apply(args:Array[String]):Unit = WebRunner.main(args)
}

object ProcessRasters {
  def apply(args:Array[String]):Unit = Steps.allOfThem()
}
