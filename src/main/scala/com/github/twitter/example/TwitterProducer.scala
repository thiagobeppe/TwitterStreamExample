package com.github.twitter.example

import com.github.twitter.example.utils.Utils
import twitter4j.{FilterQuery, TwitterFactory, TwitterStreamFactory}



object TwitterProducer extends App {

  val twitterStream = new TwitterStreamFactory(Utils.config).getInstance

  twitterStream.addListener(Utils.simpleStatusListener)
  twitterStream.filter(new FilterQuery(Array(1344951,5988062,807095,3108351)))
  Thread.sleep(10000)
  twitterStream.cleanUp
  twitterStream.shutdown


}
