package org.fedoraproject.androidpusher

import scala.sys.process._
import scalaz._, Scalaz._
import scalaz.effect.IO
import scalaz.effect.IO._

case class CommandResult(stdout: List[String], stderr: List[String], exit: Int) {
  def toDisjunction =
    if (exit == 0) {
      this.copy().right
    } else {
      this.copy().left
    }
}

sealed trait Shell {
  def execute(command: String, args: List[String], env: Map[String, String]) =
    IO {
      val stdout = new scala.collection.mutable.ListBuffer[String]
      val stderr = new scala.collection.mutable.ListBuffer[String]
      val logger = ProcessLogger(stdout.append(_), stderr.append(_))
      val exit   = Process(command +: args, None, env.toSeq:_*) ! logger
      CommandResult(stdout.toList, stderr.toList, exit)
    }
}

object Shell extends Shell
