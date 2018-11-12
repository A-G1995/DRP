package rest

import config.RedisConfig
import database.DatabaseService
import cache.RedisService
import extensions.sendJson
import kafka.KafkaFactory
import spark.Service


val config = RedisConfig()
private val http = Service.ignite().ipAddress(config.ip).port(config.port)
private val redisService = RedisService()
private val databaseService = DatabaseService()
private val kafkaService = KafkaFactory()

fun kafkaRestTest(){

    http.get("/report") { _, resp ->
        resp.sendJson(kafkaService.kafkaProducer())


    }
}


fun redisRestTest(){

    http.get("/report") { _, resp ->
        resp.sendJson(redisService.getRedisInfo())


    }
}

fun databaseTest(){

    http.get("/report") { _, resp ->

        resp.sendJson(databaseService.checkInsertToDatabase())

    }
}


fun main(args: Array<String>) {

    kafkaRestTest()
  //  redisRestTest()
//    databaseTest()

}