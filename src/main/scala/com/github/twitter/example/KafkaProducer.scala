package com.github.twitter.example

import java.util.Properties
import java.util.UUID.randomUUID

import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}
import org.slf4j.{ LoggerFactory}
import twitter4j.Status

object KafkaProducer {
  val log = LoggerFactory.getLogger(getClass.getSimpleName)
  val props = new Properties()
  props.put("bootstrap.servers","localhost:9092")
  props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer")
  props.put("acks","1")

  val producer = new KafkaProducer[String,String](props)
  val topic = "TWEETS_TOPIC"

  def run (status: Status): Unit ={
      val record = new ProducerRecord[String,String](topic,
        randomUUID().toString,
        s"Usuário: ${status.getUser.getName} (${status.getUser.getScreenName})\nTweet: ${status.getText}")
      val metadata = producer.send(record, new Callback {
        override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
          if( exception != null) log.error("Error while attempting to send message!", exception)
          else  log.info(s"The record was sent with key: ${record.key()}, value: ${record.value()}. To the partition #${metadata.partition()} and the offset #${metadata.offset()} \n")
        }
      })

  }
}
