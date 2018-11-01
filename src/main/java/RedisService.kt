
import org.apache.log4j.Logger
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig


class RedisService {

    val logger = Logger.getLogger("logger")

    val jedisPoolConfig = JedisPoolConfig()
    val jedisPool = JedisPool(jedisPoolConfig, "localhost", 6379)

    fun getRedisInfo() : String{
        try {
            jedisPool.resource.use {
                it.set("DRP_TEST", "1")
                logger.info("redis status ok")
                return "redis status ok"
            }
        } catch (e: Exception) {
            logger.warn(e.message)
            return e.message.toString()
        }
    }

}