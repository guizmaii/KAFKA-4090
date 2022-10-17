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
  kafkaProducerConfigs.setProperty("ack", "all")
  kafkaProducerConfigs.setProperty("timeout.ms", "600000000")
  kafkaProducerConfigs.setProperty("max.block.ms", "600000000")
  kafkaProducerConfigs.setProperty("batch.size", "1")
  kafkaProducerConfigs.setProperty("delivery.timeout.ms", "500")
  kafkaProducerConfigs.setProperty("request.timeout.ms", "500")
  kafkaProducerConfigs.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
  kafkaProducerConfigs.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)

  try {
    val producer: KafkaProducer[String, String] = new KafkaProducer(kafkaProducerConfigs)
    try {
      System.out.println("Created Kafka producer")
      val topicName: String = "oom-test"
      val message: String = "Hello OOM!"
      // send a message to the topic
      val recordMetadataFuture_0: Future[RecordMetadata] = producer.send(new ProducerRecord[String, String](topicName, message))
      System.out.println("0 - Sent")
      val recordMetadataFuture_1: Future[RecordMetadata] = producer.send(new ProducerRecord[String, String](topicName, message))
      System.out.println("1 - Sent")
      val recordMetadataFuture_2: Future[RecordMetadata] = producer.send(new ProducerRecord[String, String](topicName, message))
      System.out.println("2 - Sent")
      val recordMetadataFuture_3: Future[RecordMetadata] = producer.send(new ProducerRecord[String, String](topicName, message))
      System.out.println("3 - Sent")
      val recordMetadataFuture_4: Future[RecordMetadata] = producer.send(new ProducerRecord[String, String](topicName, message))
      System.out.println("4 - Sent")
      val recordMetadataFuture_5: Future[RecordMetadata] = producer.send(new ProducerRecord[String, String](topicName, message))
      System.out.println("5 - Sent")
      val sentRecordMetadata_0 = recordMetadataFuture_0.get
      System.out.println("0 - Received")
      val sentRecordMetadata_1 = recordMetadataFuture_1.get
      System.out.println("1 - Received")
      val sentRecordMetadata_2 = recordMetadataFuture_2.get
      System.out.println("2 - Received")
      val sentRecordMetadata_3 = recordMetadataFuture_3.get
      System.out.println("3 - Received")
      val sentRecordMetadata_4 = recordMetadataFuture_4.get
      System.out.println("4 - Received")
      val sentRecordMetadata_5 = recordMetadataFuture_5.get
      System.out.println("5 - Received")

      System.out.println("Sent message '" + message + "' to topic '" + topicName + "'")
    } finally if (producer != null) producer.close()
  }
  System.out.println("Tests complete")

}