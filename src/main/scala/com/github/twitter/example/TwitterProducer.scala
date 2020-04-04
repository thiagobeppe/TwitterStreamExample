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
//    twitterStream.filter(new FilterQuery(Array(1344951,5988062,807095,3108351)))
//    twitterStream.filter(new FilterQuery().track(Array("COVID-19", "#COVID", "CORONA")))
    twitterStream.filter(new FilterQuery().locations(Array(-38.54, -3.71, -38.32, -3.43 ).map(_.toDouble).grouped(2).toArray))
    Thread.sleep(50000)
    finishingLoop = false
  }
  twitterStream.cleanUp
  twitterStream.shutdown


}
