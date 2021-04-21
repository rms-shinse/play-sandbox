name := """sandbox-play"""
organization := "jp.co.recruitms"

version := "1.0.0"

scalaVersion := "2.13.5"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

lazy val devtools = (project in file("modules/devtools")).enablePlugins(PlayJava)
lazy val health   = (project in file("modules/healthcheck")).enablePlugins(PlayJava, SwaggerPlugin)

lazy val root = (project in file(".")).enablePlugins(PlayScala, SwaggerPlugin).aggregate(devtools, health).dependsOn(devtools, health)

swaggerTarget := new File("./specs/swagger")
swaggerPrettyJson := true
swaggerFileName := "apiSpecs.json"
swaggerV3 := true
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "jp.co.recruitms.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "jp.co.recruitms.binders._"
