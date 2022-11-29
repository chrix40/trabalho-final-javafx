package br.sapiens.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {
    private static Connection conn = null;
    public ConnectionSingleton() throws SQLException {
        String jdbcURL = "jdbc:h2:mem:test";
        if(conn == null) {
            conn = DriverManager.getConnection(jdbcURL);
        }
    }

    public Connection getConn(){
        return conn;
    }
}
