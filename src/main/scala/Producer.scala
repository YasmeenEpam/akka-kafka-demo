import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

class Producer {

  def writeToKafka(topic:String):Unit={
    val props=new Properties()
      props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
    val producer=new KafkaProducer[String,String](props)
    val record=new ProducerRecord[String,String](topic,"ScalaTraining","AkkaTraining")
    producer.send(record)
    producer.close()
  }

}


object ProducerStarter{
  def main(args: Array[String]): Unit = {
  //  new Producer().writeToKafka("topic1")
    val starter=new Producer()
    println(starter.writeToKafka("test"))
  }
}