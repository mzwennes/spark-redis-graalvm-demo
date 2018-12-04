package inventory

import redis.clients.jedis.Jedis
import spark.kotlin.*

class RedisClient {
    private val client: Jedis = Jedis("localhost")

    fun get(key: String): String = client.get(key)

    fun set(key: String, value: String) {
        client.set(key, value)
    }
}

fun main(args: Array<String>) {
    val http: Http = ignite()
    val redis = RedisClient()

    http.get("/hello") {
        redis.set("foo", "bar")
        redis.get("foo")
    }
}