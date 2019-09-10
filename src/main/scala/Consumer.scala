import java.util
import java.util.Properties

import org.apache.kafka.clients.consumer.KafkaConsumer
import scala.collection.JavaConverters._
class Consumer {

  def consumeFromKafka(topic:String)={

    val props=new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer")
    props.put("auto.offset.reset","latest")
    props.put("group.id","consumer-group")
    val consumer:KafkaConsumer[String,String]=new KafkaConsumer[String,String](props)
    consumer.subscribe(util.Arrays.asList(topic))
    while(true){
      val record=consumer.poll(50000).asScala
      for(data <- record.iterator)
        println(data.value())

    }
  }
}

object ConsumerStarter{
  def main(args: Array[String]): Unit = {
   // new Consumer().consumeFromKafka("topic1")
    new Consumer().consumeFromKafka("test")
  }
}
