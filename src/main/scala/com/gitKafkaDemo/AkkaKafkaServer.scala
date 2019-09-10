package com.gitKafkaDemo

import akka.Done
import akka.actor.{ActorSystem, CoordinatedShutdown}
import akka.http.scaladsl.model.{HttpRequest, MediaRanges}
import akka.http.scaladsl.model.headers.Accept
import akka.stream.{ActorMaterializer, Materializer}
import akka.http.scaladsl._
import akka.kafka.ProducerSettings
import akka.stream.scaladsl.{Sink, Source}
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}
import org.apache.kafka.clients.producer.ProducerRecord
import akka.kafka.scaladsl.{Consumer, Producer}


import scala.concurrent.{ExecutionContext, Future}

object AkkaKafkaServer extends App{
  implicit val actorSystem = ActorSystem("alpakka-kafka-demo")
  implicit val executor:ExecutionContext=actorSystem.dispatcher
  implicit val materializer: Materializer = ActorMaterializer()

  val httpRequest = HttpRequest(uri = "https://api.github.com/users?q=language:java+language:scala+location:India&per_page=10")
    .withHeaders(Accept(MediaRanges.`text/*`))

  val bootstrapServer = "localhost:9092"

  val kafkaProducerSettings = ProducerSettings(actorSystem, new StringSerializer, new StringSerializer)
    .withBootstrapServers(bootstrapServer)

  val cs: CoordinatedShutdown = CoordinatedShutdown(actorSystem)
  cs.addTask(CoordinatedShutdown.PhaseServiceStop, "shut-down-client-http-pool")( () =>
    Http().shutdownAllConnectionPools().map(_ => Done)
  )

  val future:Future[Done]=Source
    .single(httpRequest)
    .mapAsync(1)(Http().singleRequest(_))
    .map(_.toString())
    .map{elem => new ProducerRecord[String,String]("topic1", elem)
    }.runWith(Producer.plainSink(kafkaProducerSettings))
  println(httpRequest)
  println(Source
    .single(httpRequest)
    .mapAsync(1)(Http().singleRequest(_))
    .map(_.toString())
    .map{elem => new ProducerRecord[String,String]("topic1", elem)

    })
  println(future)
  future.map{ _ =>
    println("Done!")
    cs.run(CoordinatedShutdown.UnknownReason)
  }


}
