# hocon-exts

[![Build Status](https://img.shields.io/github/workflow/status/izharahmd/hocon-exts/CI)](https://github.com/izharahmd/hocon-exts/actions?query=workflow%3ACI)
[![codecov](https://codecov.io/gh/izharahmd/hocon-exts/branch/master/graph/badge.svg)](https://codecov.io/gh/izharahmd/hocon-exts)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.izharahmd/hocon-exts_2.13/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.izharahmd/hocon-exts_2.13)

Scala extensions for HOCON.

### Getting started
```scala
libraryDependencies += "io.github.izharahmed" %% "hocon-exts" % "0.0.1-RC"
```

### Usage
```scala
import io.github.izharahmd.hoconexts.Extensions._

scala> val config = """ {"a": "b"} """.toConfig
val config: com.typesafe.config.Config = Config(SimpleConfigObject({"a":"b"}))

scala> config.getStringOption("a")
val res3: Option[String] = Some(b)

scala> config.getStringOption("x")
val res4: Option[String] = None
```
