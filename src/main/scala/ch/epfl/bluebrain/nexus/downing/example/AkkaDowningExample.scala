package ch.epfl.bluebrain.nexus.downing.example

import akka.actor.{ActorSystem, Props}
import akka.cluster.Cluster
import akka.cluster.singleton.{
  ClusterSingletonManager,
  ClusterSingletonManagerSettings
}
import akka.management.scaladsl.AkkaManagement
import ch.epfl.bluebrain.nexus.downing.example.SingletonActor.Msg.End

object AkkaDowningExample extends App {
  val system = ActorSystem("ClusterSystem")
  val cluster = Cluster(system)
  AkkaManagement(system).start()
  system.actorOf(
    ClusterSingletonManager.props(singletonProps = Props(new SingletonActor),
                                  terminationMessage = End,
                                  settings = ClusterSingletonManagerSettings(system)), name = "consumer"
  )
}
