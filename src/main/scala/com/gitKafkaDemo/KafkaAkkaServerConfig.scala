package com.gitKafkaDemo

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route

import scala.concurrent.ExecutionContext

object KafkaAkkaServerConfig extends App {

  val config=ConfigFactory.load()
  val host=config.getString("http.host")
  val port=config.getString("http.port")

  implicit val system:ActorSystem=ActorSystem()
  implicit val executor:ExecutionContext=system.dispatcher
  val materializer: ActorMaterializer = ActorMaterializer()

//  val route:Route=
//    pathPrefix()
//
//
//  val api = routes
//
//  Http().bindAndHandle(handler = api, interface = host, port = port) map { binding =>
//    println(s"REST interface bound to ${binding.localAddress}") } recover { case ex =>
//    println(s"REST interface could not bind to $host:$port", ex.getMessage)
//  }
}
