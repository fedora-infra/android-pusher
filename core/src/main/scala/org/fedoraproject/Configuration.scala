package org.fedoraproject.androidpusher

import kadai.config._
import scalaz.effect.IO

object PusherConfiguration {
  def getConfig(path: String): IO[Configuration] = IO {
    Configuration.load(path)
  }
}
