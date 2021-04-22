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
  swaggerPrettyJson := true,
  swaggerDomainNameSpaces := Seq("models")
)

lazy val database = (project in file("modules/database"))
  .settings(commonSettings)

lazy val auth = (project in file("modules/auth"))
  .settings(commonSettings)
  .enablePlugins(PlayJava, SwaggerPlugin)
  .dependsOn(database)
lazy val devtools = (project in file("modules/devtools"))
  .settings(commonSettings)
  .enablePlugins(PlayJava)
lazy val health = (project in file("modules/healthcheck"))
  .settings(commonSettings)
  .enablePlugins(PlayJava, SwaggerPlugin)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .enablePlugins(PlayScala, SwaggerPlugin)
  .aggregate(auth, database, devtools, health)
  .dependsOn(auth, database, devtools, health)

swaggerFileName := "apiSpecs.json"
