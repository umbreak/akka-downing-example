mainClass in Compile := Some("ch.epfl.bluebrain.nexus.downing.example.AkkaDowningExample")

name := "akka-cluster-downing-example"

version := "0.1"

scalaVersion := "2.12.8"

enablePlugins(UniversalPlugin)
enablePlugins(JavaAppPackaging)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-cluster" % "2.5.23",
  "com.typesafe.akka" %% "akka-cluster-tools" % "2.5.23",
  "com.lightbend.akka.management" %% "akka-management-cluster-http" % "1.0.1",
  "ch.epfl.bluebrain.nexus" %% "akka-downing" % "0.13.55"
)
