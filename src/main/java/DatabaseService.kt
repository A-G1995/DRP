
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger

val logger = Logger.getLogger("logger")
val appConfig = DatabaseConfig()

class DatabaseService{


    private var datasource: DataSource? = null

    fun getDataSource(): DataSource {
        if (datasource == null) {
            val config = HikariConfig()

            config.jdbcUrl = appConfig.jdbcUrl
            config.username = appConfig.username
            config.password = appConfig.password
            config.maximumPoolSize = 10
            config.isAutoCommit = false
            config.minimumIdle = 1
            config.maximumPoolSize = 2
            config.addDataSourceProperty("cachePrepStmts", "true")
            config.addDataSourceProperty("prepStmtCacheSize", "250")
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
            config.addDataSourceProperty("serverName", "localhost");
            config.addDataSourceProperty("port", "3306");
            config.addDataSourceProperty("databaseName", "test_db");

            datasource = HikariDataSource(config)
        }
        return datasource as DataSource
    }

    fun checkInsertToDB() : String{
        if(datasource!=null) {
            var connection: Connection? = null
            val dataSource = DatabaseService().getDataSource()
        try {
            dataSource.connection.use {
                it.prepareStatement(" insert into users(name, family, age) " + " values (?, ?, ?);").use{ statement ->
                    statement.setString(1, "test3")
                    statement.setString(2, "test4")
                    statement.setInt(3, 15 )

                    statement.executeUpdate()
                }
                it.commit()

            }
            logger.info("service is up")
            return "service is up"

        } catch (e: Exception) {

            try {
                connection!!.rollback()

            } catch (e1: SQLException) {
                //e1.printStackTrace()
                logger.warn(e1)
                return e1.toString()
            }

            logger.warn(e)
            return e.toString()
            //e.printStackTrace()
        }
    }
    else {
        logger.warn("service down")
        return "service down"

    }
}


}


























/*

object DataSource {



}


class DBHandler {



}



fun main(args: Array<String>) {



    val config = HikariConfig()

    config.jdbcUrl = "jdbc:mysql://localhost:3306/books"
    config.username = "root@localhost"
    config.password = ""
    config.maximumPoolSize = 10
    config.addDataSourceProperty("cachePrepStmts", "true")
    config.addDataSourceProperty("prepStmtCacheSize", "250")
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
    ds = HikariDataSource(config)

}*/
