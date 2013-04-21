import AssemblyKeys._

name := "Seeing Water From Space"

scalaVersion := "2.10.0"

resolvers +=       "Geotools" at "http://download.osgeo.org/webdav/geotools/"

resolvers ++= Seq(
      "NL4J Repository" at "http://nativelibs4java.sourceforge.net/maven/",
      "maven2 dev repository" at "http://download.java.net/maven/2",
      "Scala Test" at "http://www.scala-tools.org/repo-reloases/",
      "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/",
      "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository",
      "sonatypeSnapshots" at "http://oss.sonatype.org/content/repositories/snapshots"
    )



libraryDependencies ++= Seq(
  "com.azavea.geotrellis" %% "geotrellis-server" % "0.8.1-RC2",
   "org.geotools" % "gt-main" % "8.0-M4",		 
  "org.geotools" % "gt-jdbc" % "8.0-M4",
  "org.geotools.jdbc" % "gt-jdbc-postgis" % "8.0-M4",
  "org.geotools" % "gt-coverage" % "8.0-M4",
  "org.geotools" % "gt-coveragetools" % "8.0-M4"
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