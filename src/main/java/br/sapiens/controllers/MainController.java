package br.sapiens.controllers;

import br.sapiens.configs.ConexaoSingleton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.IOException;

public class MainController {
    @FXML
    private BorderPane painel;

    public void initialize() throws IOException, SQLException {
        var label = new Label("Sapiens");
        painel.setBottom(label);

        ConexaoSingleton s1 = new ConexaoSingleton();

        Connection conAluno = s1.getCom();
        ConexaoSingleton s2 = new ConexaoSingleton();
        Connection conMateria = s2.getCom();

        if(conAluno==conMateria)
            System.out.println("As con sao iguais");
        else
            System.out.println("Nao sao iguais");

        System.out.println("Connected to H2 in-memory database.");

        String sql = "Create table alunos (matricula int primary key, nome varchar(200))";

        Statement statement = conAluno.createStatement();

        statement.execute(sql);

        System.out.println("Created table alunos.");

        sql = "Insert into alunos (matricula, nome) values (1, 'Chris')";

        int rows = statement.executeUpdate(sql);

        if (rows > 0) {
            System.out.println("Inserted a new row.");
        }

        conAluno.close();
    }
}