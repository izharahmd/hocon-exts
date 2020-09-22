package io.github.izharahmd.hoconexts


import scala.jdk.CollectionConverters._

import com.typesafe.config.{Config, ConfigFactory}

object Extensions {

  implicit class StringExt(val str: String) extends AnyVal {

    /** Convert String to Config */
    @inline def toConfig: Config = ConfigFactory.parseString(str)
  }

  /** RichConfig provides extensions on Config */
  implicit class RichConfig(val config: Config) extends AnyVal  {

    /**
     * Get Config Value Option, if the path is present
     *
     * @param path  Path in config
     * @param f     Function to be applied on the path
     * @tparam T    Type of function return
     */
    @inline def getConfigValueOption[T](path: String, f: String => T): Option[T] = {
      if (config.hasPath(path)) {
        Some(f(path))
      } else {
        None
      }
    }

    @inline def getStringOption(path: String): Option[String] = {
      getConfigValueOption[String](path, config.getString)
    }

    @inline def getStringListOption(path: String): Option[List[String]] = {
      getConfigValueOption[List[String]](path, config.getStringList(_).asScala.toList)
    }

    @inline def getConfigOption(path: String): Option[Config] = {
      getConfigValueOption[Config](path, config.getConfig)
    }

    @inline def getConfigListOption(path: String): Option[List[Config]] = {
      getConfigValueOption[List[Config]](path, config.getConfigList(_).asScala.toList)
    }

    @inline def getAnyRefOption(path: String): Option[AnyRef] = {
      getConfigValueOption[AnyRef](path, config.getAnyRef)
    }

    @inline def getBooleanOption(path: String): Option[Boolean] = {
      getConfigValueOption[Boolean](path, config.getBoolean)
    }


    @inline def getBytesOption(path: String): Option[Long] = {
      getConfigValueOption[Long](path, config.getBytes)
    }

    @inline def getDoubleOption(path: String): Option[Double] = {
      getConfigValueOption[Double](path, config.getDouble)
    }

    @inline def getIntOption(path: String): Option[Int] = {
      getConfigValueOption[Int](path, config.getInt)
    }

    @inline def getIntListOption(path: String): Option[List[Int]] = {
      getConfigValueOption[List[Int]](path, config.getIntList(_).asScala.map(_.toInt).toList)
    }

    @inline def getLongOption(path: String): Option[Long] = {
      getConfigValueOption[Long](path, config.getLong)
    }
  }
}
