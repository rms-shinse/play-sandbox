name := """sandbox-play"""
organization := "com.github.sizer.sandbox.play"

version := "1.0.0"

scalaVersion := "2.13.5"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "org.projectlombok" % "lombok" % "1.16.18"

Global / onChangedBuildSource := ReloadOnSourceChanges

val commonSettings = Seq(
  version := "1.0.0",
  scalaVersion := "2.13.5",
  swaggerTarget := new File("./specs/swagger"),
  swaggerV3 := true,
  swaggerPrettyJson := true
)

lazy val apiCore = (project in file("modules/api-core"))
  .enablePlugins(PlayJava)
  .settings(commonSettings)
lazy val database = (project in file("modules/database"))
  .settings(commonSettings)

lazy val auth = (project in file("modules/auth"))
  .settings(commonSettings)
  .enablePlugins(PlayJava, SwaggerPlugin)
  .dependsOn(database, apiCore)
lazy val devtools = (project in file("modules/devtools"))
  .settings(commonSettings)
  .enablePlugins(PlayJava)
  .dependsOn(apiCore)
lazy val health = (project in file("modules/healthcheck"))
  .settings(commonSettings)
  .enablePlugins(PlayJava, SwaggerPlugin)
  .dependsOn(apiCore)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .enablePlugins(PlayScala, SwaggerPlugin)
  .aggregate(apiCore, auth, database, devtools, health)
  .dependsOn(apiCore, auth, database, devtools, health)

swaggerFileName := "apiSpecs.json"
swaggerDomainNameSpaces := Seq("com.github.sizer.sandbox.play.auth.controllers.requests")