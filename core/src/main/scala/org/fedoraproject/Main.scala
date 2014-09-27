package org.fedoraproject.androidpusher

import scalaz._, Scalaz._
import scalaz.effect.SafeApp
import scalaz.effect.IO._

object Main extends SafeApp {
  override def runc = for {
    conf <- PusherConfiguration.getConfig("default.conf")
    _ <- putStrLn("Hello world!")
    _ <- putStrLn(conf.option[String]("android-pusher.sdk.android-home").toString)
  } yield ()
}
