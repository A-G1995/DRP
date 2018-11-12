package config

class RedisConfig{

    var ip = "localhost"
    var port = 8080
    var connectionTimeout: Int = 1800
    var password = ""
}

class DatabaseConfig{

    var jdbcUrl =  "jdbc:mysql://localhost:3306/test_db"
    var username = "test123"
    var password = "test123"
}

 class KafkaConsumerConfig{
        val consumerBootstrapServers: List<String> = arrayListOf("192.168.1.53:9092")
        val groupId: String = "Group_id_44"
        val enableAutoCommit: Boolean = false
        val autoCommitIntervalMs: Int = 1000
        val keyDeserializer: String = "org.apache.kafka.common.serialization.StringDeserializer"
        val valueDeserializer: String = "org.apache.kafka.common.serialization.StringDeserializer"
        val autoOffsetReset: String = "earliest"
        val maxPollRecords: Int = 10000
        val maxPollIntervalMs: Int = Integer.MAX_VALUE
       // val subscription: String = "my-example-topic"
 }



class KafkaProducerConfig{
    val producerBootstrapServer = "localhost:9092"
    val keySerializer = "org.apache.kafka.common.serialization.ByteArraySerializer"
    val valueSerializer = "org.apache.kafka.common.serialization.StringSerializer"

}