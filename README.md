# Usando Twitter com Scala e Kafka 
**Este repositório foi criado para fazer a implementação em Scala do exemplo usado no curso [ Apache Kafka Series - Learn Apache Kafka for Beginners v2](https://www.udemy.com/course/apache-kafka/).**

---
#### To see this ReadMe in english [click here](https://github.com/thiagobeppe/TwitterStreamExample/blob/master/READMEEN.md) :us:
---

# Recursos necessários
* Java 11
* [Kafka](https://kafka.apache.org/downloads) 
* [Docker](https://www.docker.com/) e [Docker-Compose](https://docs.docker.com/compose/install/) (Caso você não queria rodar o kafka localmente)
* [Scala](https://www.scala-lang.org/download/) (2.12.11)
* [Spark 3](https://spark.apache.org/downloads.html)
* Uma IDE (Eclipse, Intelij...)
* Uma conta de desenvolvedor do [Twitter](https://developer.twitter.com/en)


# Como executar -> Linux
* Rode o kafka, caso seja local necessário subir o zookeeper e o kafka-server, caso seja por docker só usar ```docker-compose up -d ```

* Coloque as chaves de acesso do twitter nas variáveis de ambiente da sua IDE ([Intellij](https://www.jetbrains.com/help/objc/add-environment-variables-and-program-arguments.html), [Eclipse](https://help.eclipse.org/2019-12/index.jsp?topic=%2Forg.eclipse.cdt.doc.user%2Ftasks%2Fcdt_t_run_env.htm)). Caso você não vá subir essa aplicação no github pode-se colocar as variáveis como *String* direto no texto.

* Abra o projeto em sua IDE e execute o arquivo *TwitterProducer*
