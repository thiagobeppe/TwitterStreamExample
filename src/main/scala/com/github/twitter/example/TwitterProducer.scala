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
//    twitterStream.filter(new FilterQuery().locations(Array(-38.54, -3.71, -38.32, -3.43 ).map(_.toDouble).grouped(2).toArray))
    twitterStream.sample()
    Thread.sleep(120000)
    finishingLoop = false
  }
  twitterStream.cleanUp
  twitterStream.shutdown


}
