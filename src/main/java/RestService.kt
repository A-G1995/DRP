import spark.Service


val config = RedisConfig()
private val redisService = RedisService()
private val http = Service.ignite().ipAddress(config.ip).port(config.port)


fun redisRestTest(){

    http.get("/report") { _, resp ->
        resp.sendJson(redisService.getRedisInfo())


    }
}


fun main(args: Array<String>) {
    redisRestTest()
} 