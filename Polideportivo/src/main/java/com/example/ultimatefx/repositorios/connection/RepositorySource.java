package com.example.ultimatefx.repositorios.connection;

import com.example.ultimatefx.database.MySqlConnection;

import javax.sql.DataSource;

/**
 * Clase para aplicar características comunes entre los repositorios
 */
public class RepositorySource {

    /**
     * el cual solo es el conector a la base de datos :)
     * @see MySqlConnection#getMySQLDataSource()
     */
    protected DataSource dataSource = MySqlConnection.getInstance().getMySQLDataSource();
}
