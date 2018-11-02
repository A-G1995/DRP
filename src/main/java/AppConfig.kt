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