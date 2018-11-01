
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger

val logger = Logger.getLogger("logger")
class HikariCPTest{


    private var datasource: DataSource? = null

    fun getDataSource(): DataSource {
        if (datasource == null) {
            val config = HikariConfig()

            config.jdbcUrl = "jdbc:mysql://localhost:3306/test_db"
            config.username = "test123"
            config.password = "test123"

            config.maximumPoolSize = 10
            config.isAutoCommit = false
            config.addDataSourceProperty("cachePrepStmts", "true")
            config.addDataSourceProperty("prepStmtCacheSize", "250")
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
            config.setMinimumIdle(1);
            config.setMaximumPoolSize(2);
            //config.setInitializationFailFast(true)
            //config.setConnectionTestQuery("VALUES 1");
            config.dataSourceClassName = "com.mysql.jdbc.jdbc2.optional.MysqlDataSource";
            config.addDataSourceProperty("serverName", "localhost");
            config.addDataSourceProperty("port", "3306");
            config.addDataSourceProperty("databaseName", "test_db");
            config.addDataSourceProperty("user", "test123");
            config.addDataSourceProperty("password", "test123");

            datasource = HikariDataSource(config)
        }
        return datasource as DataSource
    }


}


fun main(args: Array<String>) {

    var connection: Connection? = null
    val dataSource = HikariCPTest().getDataSource()
    try {
        dataSource.connection.use {
            it.prepareStatement(" insert into users(name, family, age) " + " values (?, ?, ?);").use{ statement ->
                statement.setString(1, "Ali")
                statement.setString(2, "Fallahi")
                statement.setInt(3, 25 )

                statement.executeQuery()
            }
            it.commit()

        }

    } catch (e: Exception) {
        try {
            connection!!.rollback()
        } catch (e1: SQLException) {
            e1.printStackTrace()
        }

        e.printStackTrace()
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
