package com.github.twitter.example.kafka

import java.util.Properties

import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.{KafkaStreams, StreamsBuilder, StreamsConfig}
import com.github.twitter.example.utils.Utils.{extractUserFollowersFromJson}

object KafkaStream extends App{
  //Create the properties
  val props = new Properties()
  props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
  props.put(StreamsConfig.APPLICATION_ID_CONFIG, "twitter-filter-stream")
  props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass)
  props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass)

  //create a topology
  val builder = new StreamsBuilder()

  //Input Topic
  val sourceStream: KStream[String, String] = builder.stream[String,String]("TWEETS_TOPIC")

  //filter tweet
  val filteredStream = sourceStream.filter( (key, value) => {
    extractUserFollowersFromJson(value) >= 10000
  })
  filteredStream.to("IMPORTANT_TWEETS")

  //build the topology
  val streams: KafkaStreams = new KafkaStreams(builder.build(), props)

  //Start our streams

  streams.cleanUp()
  streams.start()
}
