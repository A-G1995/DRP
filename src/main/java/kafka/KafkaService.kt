package kafka


import config.KafkaConsumerConfig
import config.KafkaProducerConfig
import kafka.common.KafkaException
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.log4j.Logger
import java.util.Properties
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord


class KafkaFactory

{

    private val logger = Logger.getLogger("kafka.getLogger")
    private val consumerConfig = KafkaConsumerConfig()
    private val producerConfig = KafkaProducerConfig()

    fun kafkaProducer() : String{
        val producer :  KafkaProducer<String, String>
        val configProperties = Properties()
        configProperties[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = producerConfig.producerBootstrapServer
        configProperties[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = producerConfig.keySerializer
        configProperties[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = producerConfig.valueSerializer
        try {

            producer = KafkaProducer(configProperties)
        }catch (e:Exception){
            return "error in produce !"
        }
        val rec = ProducerRecord<String, String>("my-example-topic", "test")
        for (i in 0..10){
            producer.send(rec)
        }
    return "service is up !"

    }

    fun createKafkaConsumer() : String {

        val props = Properties()
        props["bootstrap.servers"] = consumerConfig.consumerBootstrapServers
        props["group.id"] = consumerConfig.groupId
        props["enable.auto.commit"] = consumerConfig.enableAutoCommit
        props["auto.commit.interval.ms"] = consumerConfig.autoCommitIntervalMs
        props["key.deserializer"] = consumerConfig.keyDeserializer
        props["value.deserializer"] = consumerConfig.valueDeserializer
        props["auto.offset.reset"] = consumerConfig.autoOffsetReset
        props["max.poll.records"] = consumerConfig.maxPollRecords
        props["max.poll.interval.ms"] = consumerConfig.maxPollIntervalMs

        var consumer : KafkaConsumer<String, String>? = null

        try
        {
            consumer = KafkaConsumer(props)
            return "service up"
        }
        catch (e: KafkaException)
        {
            logger.error("service down")
            return "service down"

        }


    }
}
