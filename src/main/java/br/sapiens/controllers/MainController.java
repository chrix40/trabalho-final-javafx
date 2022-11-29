package br.sapiens.controllers;

import br.sapiens.Main;
import br.sapiens.configs.ConnectionSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.sql.Connection;

public class MainController {
    @FXML
    private BorderPane pane;

    public void cadastrarAluno() throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource("/aluno/cadastro.fxml"));
        pane.setCenter(fxmlLoader.load());
    }


    public void listarAluno() throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource("/aluno/litaAluno.fxml"));
        pane.setCenter(fxmlLoader.load());
    }


    public void cadastrarDisciplina() throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource("/disciplina/cadastro.fxml"));
        pane.setCenter(fxmlLoader.load());
    }


    public void listarDisciplina() throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource("/disciplina/listar.fxml"));
        pane.setCenter(fxmlLoader.load());
    }

    public void listarMatricula() throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource("/matricula/listaMatricula.fxml"));
        pane.setCenter(fxmlLoader.load());
    }



    public void initialize() throws IOException {
        var label = new Label("Sapiens");
        pane.setBottom(label);
    }

}