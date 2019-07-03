package ch.epfl.bluebrain.nexus.downing.example

import akka.actor.{Actor, ActorLogging, Cancellable}
import ch.epfl.bluebrain.nexus.downing.example.SingletonActor.Msg.{End, Ping}

import scala.concurrent.duration._

class SingletonActor extends Actor with ActorLogging {

  private var timer = Option.empty[Cancellable]
  private implicit val ec = context.system.dispatcher

  override def preStart() = {
    log.info("Starting cluster singleton")
    self ! Ping
  }



  override def receive: Receive = {
    case Ping =>
      log.info("Received Ping message")
      timer = Some(context.system.scheduler.scheduleOnce(4 second, self, Ping))
    case End =>
      log.info("Stopping cluster singleton")
      context.stop(self)
  }
}

object SingletonActor {
  sealed trait Msg
  object Msg {
    final case object Start extends Msg
    final case object Ping extends Msg
    final case object End extends Msg
  }
}
