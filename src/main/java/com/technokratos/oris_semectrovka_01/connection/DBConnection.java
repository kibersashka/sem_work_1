package com.technokratos.oris_semectrovka_01.connection;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class DBConnection {
    private static DataSource dataSourse;
    private static final PropertiesLoader propsLoader =  new PropertiesLoader();

    public static void init() throws ClassNotFoundException{
        Class.forName("org.postgresql.Driver");

        HikariConfig config = new HikariConfig();
        System.out.println(propsLoader.getProperty("database.url"));
        config.setJdbcUrl(propsLoader.getProperty("database.url"));
        config.setUsername(propsLoader.getProperty("database.username"));
        config.setPassword(propsLoader.getProperty("database.password"));
        config.setMaximumPoolSize(Integer.parseInt(propsLoader.getProperty("hikari.poolsize")));

        dataSourse = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        if (dataSourse != null) {
            return dataSourse.getConnection();
        }

        try {
            init();
            return dataSourse.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void destroy() {
        ((HikariDataSource)dataSourse).close();
    }

}
