package com.github.twitter.example.kafka

import java.util.Properties
import java.util.UUID.randomUUID

import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}
import org.slf4j.LoggerFactory
import twitter4j.Status

object KafkaProducer {
  val log = LoggerFactory.getLogger(getClass.getSimpleName)
  val props = new Properties()
  props.put("bootstrap.servers","localhost:9092")
  props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer")

  //If the producer doesn't receive an ack by all the topics, it will send the message again.
  props.put("acks","all")

  //How much time the producer will retry the sending of the message, after this time it will throw an exception
  props.put("delivery.timeout.ms", "60000")

  //How much requests can be sent in parallel to the broker
  props.put("max.in.flight.requests.per.connection", "5")

  //Transform the producer on an idempotent producer, this guarantees a stable and safe pipeline
  props.put("enable.idempotence", "true")
  //If your replication factor is 3, and this value is 2, means the producer will receive an error if has more than 1
  //broker down
  props.put("min.insync.replicas", "2")

  //Adding a parameters to a high throughput
  props.put("compression.type", "snappy")
  props.put("linger.ms", "20")
  props.put("batch.size", "16384")


  val producer = new KafkaProducer[String,String](props)
  val topic = "TWEETS_TOPIC"

  def run (status: String): Unit ={
      val record = new ProducerRecord[String,String](topic,
        randomUUID().toString,
        status)
      val metadata = producer.send(record, new Callback {
        override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
          if( exception != null) log.error("Error while attempting to send message!", exception)
          else log.info(s"The record was sent with key: ${record.key()}, value: ${record.value()}. To the partition #${metadata.partition()} and the offset #${metadata.offset()} \n")
        }
      })

  }
}
