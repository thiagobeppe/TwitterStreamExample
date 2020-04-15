name := "TwitterStreamKafka"

version := "0.1"

scalaVersion := "2.12.11"

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.4.1"
libraryDependencies += "org.apache.kafka" % "kafka-streams" % "2.5.0"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.30"
libraryDependencies += "org.twitter4j" % "twitter4j-stream" % "3.0.3"
libraryDependencies += "org.elasticsearch" % "elasticsearch" % "7.6.2"
libraryDependencies += "org.elasticsearch.client" % "elasticsearch-rest-high-level-client" % "7.6.2"
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.6"






