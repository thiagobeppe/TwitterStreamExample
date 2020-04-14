package com.github.twitter.example.utils

import org.apache.http.HttpHost
import org.apache.http.auth.{AuthScope, UsernamePasswordCredentials}
import org.apache.http.client.CredentialsProvider
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder
import org.elasticsearch.action.index.{IndexRequest, IndexResponse}
import org.elasticsearch.client.{RequestOptions, RestClient, RestClientBuilder, RestHighLevelClient}
import org.elasticsearch.common.xcontent.XContentType

import com.github.twitter.example.utils.Utils.extractIdFromJson
class ElasticSearchConfig {

  private val port = System.getenv("ELASTIC_PORT")
  private val host = System.getenv("ELASTIC_HOST")
  private val scheme = "https"
  private val username = System.getenv("ELASTIC_USERNAME")
  private val password = System.getenv("ELASTIC_PASSWORD")

  val cp: CredentialsProvider = new BasicCredentialsProvider()
  cp.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username,password))

  val client = new RestHighLevelClient(
    RestClient.builder(new HttpHost(host, port.toInt, scheme)).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback {
        override def customizeHttpClient(httpClientBuilder: HttpAsyncClientBuilder): HttpAsyncClientBuilder = httpClientBuilder.setDefaultCredentialsProvider(cp)
      }
    )
  )

  def insertIntoElastic(record: String) = {
    val request: IndexRequest = new IndexRequest("twitter")
    val id = extractIdFromJson(record)
    request.id(id)
    request.source(record, XContentType.JSON)
    Thread.sleep(1000)
    val response: IndexResponse = client.index(request, RequestOptions.DEFAULT)
    println(response.getId)
  }

}
