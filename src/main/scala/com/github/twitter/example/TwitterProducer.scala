package com.github.twitter.example

import com.github.twitter.example.utils.Utils
import twitter4j.{FilterQuery, TwitterStreamFactory}



object TwitterProducer extends App {

  var finishingLoop = true

  //Create a twitter connect
  val twitterStream = new TwitterStreamFactory(Utils.config).getInstance
  twitterStream.addListener(Utils.simpleStatusListener)

  //Create a loop to send msgs to kafka
  while(finishingLoop){
    twitterStream.filter(new FilterQuery().track(Array("COVID", "CORONA", "PANDEMIA")))
    Thread.sleep(120000)
    finishingLoop = false
  }
  twitterStream.cleanUp
  twitterStream.shutdown


}
