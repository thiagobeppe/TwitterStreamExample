package com.github.twitter.example.kafka

import java.util.Properties

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.JavaConverters._
import java.time.Duration

import com.github.twitter.example.utils.ElasticSearchConfig

object KafkaConsumer extends App {
  val log: Logger = LoggerFactory.getLogger(getClass.getSimpleName)
  private val elasticUtils: ElasticSearchConfig = new  ElasticSearchConfig()
  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("group.id", getClass.getSimpleName)
  props.put("auto.offset.reset", "earliest")

  val consumer = new KafkaConsumer[String, String](props)
  consumer.subscribe(List("TWEETS_TOPIC").asJava)

  while(true){
    val records = consumer.poll(Duration.ofMillis(100))
    records.forEach( record => {
      elasticUtils.insertIntoElastic(record.value())
    })
  }

}
