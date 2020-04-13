package com.github.twitter.example

import java.util.Date

import org.apache.http.HttpHost
import org.apache.http.auth.{AuthScope, UsernamePasswordCredentials}
import org.apache.http.client.CredentialsProvider
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder
import org.elasticsearch.action.index.{IndexRequest, IndexResponse}
import org.elasticsearch.client.{RequestOptions, RestClient, RestClientBuilder, RestHighLevelClient}
import org.elasticsearch.common.xcontent.XContentType


object ElasticSearchConfig extends App{
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
  val request: IndexRequest = new IndexRequest("twitter")
  request.id("1")
  request.source("name","thiago","postDate", new Date(), "Message", "trying out Elasticsearch")
  val response: IndexResponse = client.index(request, RequestOptions.DEFAULT)
  println(response.getId)
}
