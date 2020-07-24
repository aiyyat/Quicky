name := """quiz-admin-service"""
organization := "com.company"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.2"

libraryDependencies ++= Seq(
  guice,
  javaJpa,
  "org.projectlombok" % "lombok" % "1.18.12" % "provided",
  "org.hibernate" % "hibernate-core" % "5.4.2.Final",
  "org.postgresql" % "postgresql" % "42.2.5"
)
logLevel := util.Level.Debug

enablePlugins(FlywayPlugin)
flywayUrl := "jdbc:postgresql://192.168.1.116/postgres"
flywayUser := "postgres"
flywayPassword := "mysecretpassword"
flywayLocations += "filesystem:resources/db"
