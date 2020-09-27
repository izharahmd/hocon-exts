package io.github.izharahmd.hoconexts

import com.typesafe.config.{Config, ConfigFactory}

class ExtensionsTest extends munit.FunSuite {
  import io.github.izharahmd.hoconexts.Extensions._

  test("String to Config") {
    val actualString = "{a: b}"
    val actualConfig = actualString.toConfig
    val expectedConfig = ConfigFactory.parseString("{a: b}")
    assertEquals(actualConfig, expectedConfig)
  }

  test("Get string from Config") {
    val actualConfig = ConfigFactory.parseString("{a: b}".stripMargin)
    val actualString = actualConfig.getStringOption("a")
    val expectedString = Some("b")
    assertEquals(actualString, expectedString)
  }

  test("Get string from Config - Path not present") {
    val actualConfig = ConfigFactory.parseString("{a: b}")
    val actualString = actualConfig.getStringOption("b")
    val expectedString = None
    assertEquals(actualString, expectedString)
  }

  test("Get string list from Config") {
    val actualConfig = ConfigFactory.parseString("{a: [b, c]}")
    val actualStringList = actualConfig.getStringListOption("a")
    val expectedStringList = Some(List("b", "c"))
    assertEquals(actualStringList, expectedStringList)
  }

  test("Get long from Config") {
    val actualConfig = ConfigFactory.parseString("{a: 1563285030000}")
    val actualLong = actualConfig.getLongOption("a")
    val expectedLong = Some(1563285030000L)
    assertEquals(actualLong, expectedLong)
  }

  test("Get boolean from Config") {
    val actualConfig = ConfigFactory.parseString("{a: true}")
    val actualBoolean = actualConfig.getBooleanOption("a")
    val expectedBoolean = Some(true)
    assertEquals(actualBoolean, expectedBoolean)
  }

  test("Get Bytes from Config") {
    val actualConfig = ConfigFactory.parseString("{a: 1563285030000}")
    val actualBytes = actualConfig.getBytesOption("a")
    val expectedBytes = Some(1563285030000L)
    assertEquals(actualBytes, expectedBytes)
  }

  test("Get Config from Config") {
    val actualConfig = ConfigFactory.parseString("{a: {b: c}}")
    val actualExtractedConfig = actualConfig.getConfigOption("a")
    val expectedConfig = Some(ConfigFactory.parseString("{b: c}"))
    assertEquals(actualExtractedConfig, expectedConfig)
  }

  test("Get Config List from Config") {
    val actualConfig = ConfigFactory.parseString("{a: [{b: c}]}")
    val actualExtractedConfigList = actualConfig.getConfigListOption("a")
    val expectedConfigList = Some(List(ConfigFactory.parseString("{b: c}")))
    assertEquals(actualExtractedConfigList, expectedConfigList)
  }

  test("Get Double from Config") {
    val actualConfig = ConfigFactory.parseString("{a: 10.0}")
    val actualDouble = actualConfig.getDoubleOption("a")
    val expectedDouble = Some(10.0)
    assertEquals(actualDouble, expectedDouble)
  }

  test("Get Int from Config") {
    val actualConfig = ConfigFactory.parseString("{a: 10}")
    val actualInt = actualConfig.getIntOption("a")
    val expectedInt = Some(10)
    assertEquals(actualInt, expectedInt)
  }

  test("Get Int List from Config") {
    val actualConfig = ConfigFactory.parseString("{a: [10]}")
    val actualIntList = actualConfig.getIntListOption("a")
    val expectedIntList = Some(List(10))
    assertEquals(actualIntList, expectedIntList)
  }

  test("Get AnyRef from Config") {
    val actualConfig = ConfigFactory.parseString("{a: 10}")
    val actualAnyRef = actualConfig.getAnyRefOption("a")
    val expectedAnyRef = Some(10)
    assert(actualAnyRef == expectedAnyRef)
  }

  test("Read java file as config") {
    val file = new java.io.File(getClass.getResource("/test.conf").toURI)
    val actualFileAsConfig = file.toConfig
    // assuming toConfig on strings works
    val expectedConfig = """ {"k": "42"} """.toConfig
    assertEquals(actualFileAsConfig, expectedConfig)
  }

  test("Read java file as config negative test") {
    val file = new java.io.File(getClass.getResource("/test.conf").toURI)
    val actualFileAsConfig = file.toConfig
    // assuming toConfig on strings works
    val expectedConfig = """ {"k": "43"} """.toConfig
    assertNotEquals(actualFileAsConfig, expectedConfig)
  }

  test("Parse scala Map as config") {
    val config: Config = Map("abc" -> "1", "a" -> "2").toConfig
    val expectedConfig = """ {"abc": "1", "a": "2"} """.toConfig
    assertEquals(config, expectedConfig)
  }

  test("Parse mutable scala Map as config") {
    val mp = collection.mutable.Map("x" -> "y")
    mp += ("p" -> "p")
    val actualConfig: Config = mp.toConfig
    val expectedConfig = """ {"x": "y", "p": "p"} """.toConfig
    assertEquals(actualConfig, expectedConfig)
  }

  test("Parse scala Map with ConfigValue as config") {
    val internalConfig: Config = """ {"x": 1}""".toConfig
    val mp = Map("abc" -> "1", "inner" -> internalConfig.root())
    val actualConfig: Config = mp.toConfig
    val expectedConfig = """ {"abc": "1", "inner": {"x": 1}} """.toConfig
    assertEquals(actualConfig, expectedConfig)
  }

  test("Get keySet from config") {
    val config: Config = Map("abc" -> "1", "a" -> "2").toConfig
    val actualKeySet = config.keySet
    val expectedKeySet = Set("abc", "a")
    assertEquals(actualKeySet, expectedKeySet)
  }

  test("Get keys from Config") {
    val config: Config = Map("abc" -> "1", "a" -> "2").toConfig
    val actualKeys = config.keys
    val expectedKeys = Set("abc", "a")
    assertEquals(actualKeys, expectedKeys)
  }

  test("Get paths from Config") {
    val config: Config =
      """ {
        |"abc": "1",
        |"a": "2",
        |"c": {
        |        "d": {"f": 1},
        |        "e": 2
        |     }
        | } """.stripMargin.toConfig
    val actualPaths = config.paths
    val expectedPaths = Set("abc", "a", "c.e", "c.d.f")
    assertEquals(actualPaths, expectedPaths)
  }
}
