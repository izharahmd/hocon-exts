ThisBuild / scalaVersion     := "2.13.3"
ThisBuild / version          := "0.0.1-RC"
ThisBuild / organization     := "io.github.izharahmd"


lazy val root = (project in file("."))
  .settings(
    name := "hocon-exts",
    testFrameworks += new TestFramework("munit.Framework"),
    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % "1.4.0",
      "org.scalameta" %% "munit" % "0.7.12" % Test
    )
  )
