ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.0"

val mongodbVersion = "4.7.1"
val json4sVersion = "4.1.0-M1"
val slf4jVersion = "2.0.1"
val scalaXml = "2.1.0"

lazy val root = (project in file("."))
  .settings(
    name := "Data_processing",

    libraryDependencies ++= Seq(
      "org.mongodb" % "mongodb-driver-sync" % mongodbVersion,
      "org.json4s" %% "json4s-core" % json4sVersion,
      "org.json4s" %% "json4s-native" % json4sVersion,
      "org.json4s" %% "json4s-xml" % json4sVersion,
      "org.scala-lang.modules" %% "scala-xml" % scalaXml
    )
  )
