import akka.actor.ActorSystem
import akka.http.javadsl.Http

import scala.concurrent.ExecutionContext
import akka.stream.ActorMaterializer

object ServerExample extends App {

val host="0.0.0.0"
  val port=9000

  implicit val system:ActorSystem=ActorSystem("helloworld")
  implicit val executor:ExecutionContext=system.dispatcher
  val materializer: ActorMaterializer = ActorMaterializer()

//def route=path("hello")

  val http = Http.get(system)

}
