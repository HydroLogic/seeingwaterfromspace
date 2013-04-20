package lltowm

import geotrellis.admin.{Reproject, Projections}
import geotrellis.process.{Server, Catalog}

import geotrellis.feature.Point

case class LatLong(lat:Double,long:Double) {
  def toPoint() = {
    Point(long,lat,0)
  }
}

object Main {
  def main(args:Array[String]):Unit = {
    if(args.length != 1) {
      println("Must give comma seperated lat long values.")
      return
    }

    val latlongVals = args(0).split(",")
                          .map(_.toDouble)

    if(latlongVals.length % 2 != 0) {
      println("Must give even number of lats and longs.")
    }

    println("In Web Mercator:  " +
      latlongVals.grouped(2)
                 .map(l => LatLong(l(0),l(1)))
                 .map(_.toPoint)
                 .map(Reproject(_,Projections.LatLong,
                                  Projections.WebMercator))
                 .map(_.asInstanceOf[Point[Int]])
                 .map(p => s"${p.geom.getX},${p.geom.getY}")
                 .reduce( (a,b) => s"$a,$b"))
  }
}
