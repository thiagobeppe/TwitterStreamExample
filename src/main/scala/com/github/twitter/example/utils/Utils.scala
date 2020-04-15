package com.github.twitter.example.utils

import com.github.twitter.example.kafka.KafkaProducer
import com.google.gson.JsonParser
import twitter4j.{StallWarning, StatusDeletionNotice, StatusListener}
import twitter4j.Status
import twitter4j.json.DataObjectFactory

object Utils {
  val config = new twitter4j.conf.ConfigurationBuilder()
    .setOAuthConsumerKey(System.getenv("TWITTER_CONSUMER_TOKEN_KEY"))
    .setOAuthConsumerSecret(System.getenv("TWITTER_CONSUMER_TOKEN_SECRET"))
    .setOAuthAccessToken(System.getenv("TWITTER_ACCESS_TOKEN_KEY"))
    .setOAuthAccessTokenSecret(System.getenv("TWITTER_ACCESS_TOKEN_SECRET"))
    .setJSONStoreEnabled(true)
    .build


  def simpleStatusListener = new StatusListener() {
    def onStatus(status: Status): Unit = {
      KafkaProducer.run(DataObjectFactory.getRawJSON(status))
       }
    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
    def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
    def onException(ex: Exception) { ex.printStackTrace }
    def onScrubGeo(arg0: Long, arg1: Long) {}
    def onStallWarning(warning: StallWarning) {}
  }


  def extractIdFromJson(record: String): String = {
    JsonParser.parseString(record).getAsJsonObject().get("id").getAsString()
  }

  def extractUserFollowersFromJson(record: String): Int = {
    try {
      JsonParser.parseString(record).getAsJsonObject().get("user").getAsJsonObject().get("followers_count").getAsInt()
    }
    catch {
      case e: NullPointerException => 0
    }
  }

}