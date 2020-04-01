package com.github.twitter.example

import com.github.twitter.example.utils.Utils
import twitter4j.{FilterQuery, TwitterStreamFactory}



object TwitterProducer extends App {

  var finishingLoop = true

  //Create a twitter connect
  val twitterStream = new TwitterStreamFactory(Utils.config).getInstance
  twitterStream.addListener(Utils.simpleStatusListener)
  //Create a kafka topic

  //Create a loop to send msgs to kafka
  while(finishingLoop){

    Thread.sleep(10000)
  }
  twitterStream.cleanUp
  twitterStream.shutdown

}
