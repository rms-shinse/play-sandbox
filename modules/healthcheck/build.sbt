name := """sandbox-play-health.routes-check"""
organization := "jp.co.recruitms.health.routes"

version := "1.0-SNAPSHOT"

scalaVersion := "2.13.5"
swaggerRoutesFile := "health.routes"
swaggerPlayJava := true
swaggerTarget := new File("./specs/swagger/health")
