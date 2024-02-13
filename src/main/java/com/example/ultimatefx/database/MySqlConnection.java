package com.example.ultimatefx.database;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
/**
 * Clase que contiene el método encargado de comunicarse con la base de datos
 * */
public final class MySqlConnection {

    private static MySqlConnection instance;
    private static final String DB_PROPERTIES = "files/db.properties";

    private MySqlConnection(){}

    public static MySqlConnection getInstance(){
        if (instance == null){
            instance = new MySqlConnection();
        }
        return instance;
    }

    /**
     * @return Retorna un DataSouce con la info de la base de datos
     * @file /files/db.properties
     */
    public DataSource getMySQLDataSource() {

        Properties props = new Properties();

        MysqlDataSource mysqlDS = null;

        try (InputStream fis = Files.newInputStream(Paths.get(DB_PROPERTIES))) {

            props.load(fis);

            mysqlDS = new MysqlDataSource();

            mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));

            mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));

            mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));

        } catch (IOException e) {

            e.printStackTrace();

        }

        return mysqlDS;

    }

}
