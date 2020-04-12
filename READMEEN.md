# A Twitter's application using
**This repository was created to make an implementation of an exercise proposed by the course[ Apache Kafka Series - Learn Apache Kafka for Beginners v2](https://www.udemy.com/course/apache-kafka/).**

# Necessary Resources
* Java 11
* [Kafka](https://kafka.apache.org/downloads) 
* [Docker](https://www.docker.com/) and [Docker-Compose](https://docs.docker.com/compose/install/) (To run kafka without installing in your machine)
* [Scala](https://www.scala-lang.org/download/) (2.12.11)
* [Spark 3](https://spark.apache.org/downloads.html)
* An IDE (Eclipse, Intelij...)
* A [Twitter](https://developer.twitter.com/en) developer account


# How to Run -> Linux
* Run in your terminal ```docker-compose up -d ``` to use docker, but if you have the kafka installed locally, run zookeeper and kafka server.

* Put your twitter keys in your IDE's environment ([Intellij](https://www.jetbrains.com/help/objc/add-environment-variables-and-program-arguments.html), [Eclipse](https://help.eclipse.org/2019-12/index.jsp?topic=%2Forg.eclipse.cdt.doc.user%2Ftasks%2Fcdt_t_run_env.htm)). If you are not going to put your project on github, you can put the keys directly in the code using *String*.

* Open the project on your IDE and run the file *TwitterProducer*
