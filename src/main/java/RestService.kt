import spark.Service


val config = RedisConfig()
private val http = Service.ignite().ipAddress(config.ip).port(config.port)
private val redisService = RedisService()
private val databaseService = DatabaseService()


fun redisRestTest(){

    http.get("/report") { _, resp ->
        resp.sendJson(redisService.getRedisInfo())


    }
}

fun databaseTest(){
    http.get("/report") {_ , resp ->

        resp.sendJson(databaseService.checkInsertToDB())

    }
}


fun main(args: Array<String>) {

    //redisRestTest()
    databaseTest()

}