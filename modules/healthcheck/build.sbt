name := """sandbox-play-health"""
organization := "jp.co.recruitms.health"

version := "1.0.0"

scalaVersion := "2.13.5"
swaggerRoutesFile := "health.routes"
swaggerPlayJava := true
swaggerTarget := new File("./specs/swagger")
swaggerPrettyJson := true
swaggerFileName := "apiSpecs.health.json"
swaggerV3 := true
