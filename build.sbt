name := "TwitterStreamKafka"

version := "0.1"

scalaVersion := "2.12.11"

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.4.1"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.30"
libraryDependencies += "com.danielasfregola" %% "twitter4s" % "6.2"