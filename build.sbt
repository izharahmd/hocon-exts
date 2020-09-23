inThisBuild(
  List(
    scalaVersion := "2.13.3",
    organization := "io.github.izharahmd",
    homepage := Some(url("https://github.com/izharahmd/hocon-exts")),
    licenses := List(
      "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
    ),
    developers := List(
      Developer(
        id = "izharahmd",
        name = "Izhar Ahmed",
        email = "izhar0407@gmail.com",
        url = url("https://github.com/izharahmd/")
      )
    ),
    dynverVTagPrefix := false
  )
)

lazy val root = (project in file("."))
  .settings(
    name := "hocon-exts",
    testFrameworks += new TestFramework("munit.Framework"),
    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % "1.4.0",
      "org.scalameta" %% "munit" % "0.7.12" % Test
    )
  )
