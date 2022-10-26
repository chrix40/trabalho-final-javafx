package br.sapiens.configs;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConexaoSingleton {

    private Connection com = null;

    public ConexaoSingleton() throws SQLException {
        String jdbcURL = "jdbc:h2:mem:test";
        if (com == null) {
            com = DriverManager.getConnection(jdbcURL);
            System.out.println("Uma conexão está sendo estabelecida.");
        }
    }

    public Connection getCom(){
        return com;
    }

    public Connection getConnection(){
        return com;
    }


}
