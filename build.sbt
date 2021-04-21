name := """sandbox-play"""
organization := "jp.co.recruitms"

version := "1.0.0"

scalaVersion := "2.13.5"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

val commonSettings = Seq(
  version := "1.0.0",
  scalaVersion := "2.13.5",
  swaggerTarget := new File("./specs/swagger"),
  swaggerV3 := true,
  swaggerPrettyJson := true
)

lazy val auth     = (project in file("modules/auth"))
  .settings(commonSettings)
  .enablePlugins(PlayScala, SwaggerPlugin)
lazy val devtools = (project in file("modules/devtools"))
  .settings(commonSettings)
  .enablePlugins(PlayJava)
lazy val health   = (project in file("modules/healthcheck"))
  .settings(commonSettings)
  .enablePlugins(PlayJava, SwaggerPlugin)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .enablePlugins(PlayScala, SwaggerPlugin)
  .aggregate(auth, devtools, health)
  .dependsOn(auth, devtools, health)

swaggerTarget := new File("./specs/swagger")
swaggerPrettyJson := true
swaggerFileName := "apiSpecs.json"
swaggerV3 := true
