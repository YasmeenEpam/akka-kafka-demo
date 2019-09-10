name := "KafkaTraining"

version := "0.1"

scalaVersion := "2.12.0"

//libraryDependencies += "org.apache.kafka" %% "kafka" % "2.3.0"

libraryDependencies ++= Seq("org.apache.kafka" %% "kafka" % "2.3.0","org.slf4j" % "slf4j-api" % "1.7.5",
 // "org.slf4j" % "slf4j-simple" % "1.7.5",
  "com.typesafe.akka" %% "akka-actor" % "2.5.13",
  "com.typesafe.akka" %% "akka-http-core"  % "10.1.3",
  "com.typesafe.akka" %% "akka-stream" % "2.5.13",
  "com.typesafe.akka" %% "akka-http" % "10.1.3",
  "com.typesafe.akka" %% "akka-stream-kafka" % "1.0-RC1",
  "com.typesafe.akka" %% "akka-slf4j" % "2.5.13"
)