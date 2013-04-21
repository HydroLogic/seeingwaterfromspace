package nasa

import geotrellis._
import geotrellis.process._
import geotrellis.raster.op.transform._
import geotrellis.raster.op._
import geotrellis.data.arg._
import geotrellis.rest._

case class Rast(r:Op[Raster], name:String)

object Steps {
  def process(r:Rast,op:Op[Raster]=>Op[Raster]) = Rast(op(r.r),r.name)

  def allOfThem() = {
    GeoTrellis.setup(ServerConfig.init().geotrellis)

    val writer = ArgWriter(TypeBit)
    val extent = Extent(-8420695.567404034,-7481526.898194933,-7453944.197958334,-1988447.6861175005)

    GeoTrellis.server.catalog.stores.values
      .map( _.getNames ).flatten
      .map { n:String => Rast(io.LoadRaster(n),n) }
      .map { process(_,Crop(_,extent)) }
      .map { process( _, local.IfCell(_, {z:Int => z == 1}, 1, 0) ) }
      .map { process( _, ConvertType(_,TypeBit))  }
      .map { rast =>
        val path = s"/home/ubuntu/seeingwaterfromspace/data/cropped/${rast.name}.arg"
        (rast.r.map(writer.write(path,_,rast.name)),rast.name)
      }
      .map { t => t match { case (op,name) =>
        GeoTrellis.server.getResult(op) match {
          case Complete(_,_) => println(s"Successfully processed ${name}")
          case Error(msg,failure) => println(s"$msg - $failure")
      }}
    }
  }
}
