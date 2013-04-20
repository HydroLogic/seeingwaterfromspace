import AssemblyKeys._

name := "Lat-Long to Web Mercator translator"

scalaVersion := "2.10.0"

libraryDependencies ++= Seq(
  "com.azavea.geotrellis" %% "geotrellis-server" % "0.8.1-RC2"
)



assemblySettings

mergeStrategy in assembly <<= (mergeStrategy in assembly) {
  (old) => {
    case "reference.conf" => MergeStrategy.concat
    case "application.conf" => MergeStrategy.concat
    case "META-INF/MANIFEST.MF" => MergeStrategy.discard
    case "META-INF\\MANIFEST.MF" => MergeStrategy.discard
    case _ => MergeStrategy.first
  }
}