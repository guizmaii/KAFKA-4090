import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord, RecordMetadata}
import org.apache.kafka.common.serialization.StringSerializer

import java.util.Properties
import java.util.concurrent.Future

object Main extends App {
  //
  // Code copied from: https://issues.apache.org/jira/browse/KAFKA-4090
  //

  val kafkaProducerConfigs = new Properties();
  // NOTE: Intentionally use a SSL port without specifying security.protocol as SSL
  kafkaProducerConfigs.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093")
  kafkaProducerConfigs.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
  kafkaProducerConfigs.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)

  try {
    val producer: KafkaProducer[String, String] = new KafkaProducer(kafkaProducerConfigs)
    try {
      System.out.println("Created Kafka producer")
      val topicName: String = "oom-test"
      val message: String = "Hello OOM!"
      // send a message to the topic
      val recordMetadataFuture: Future[RecordMetadata] = producer.send(new ProducerRecord[String, String](topicName, message))
      val sentRecordMetadata = recordMetadataFuture.get
      System.out.println("Sent message '" + message + "' to topic '" + topicName + "'")
    } finally if (producer != null) producer.close()
  }
  System.out.println("Tests complete")

}