package br.sapiens.controllers;

import br.sapiens.Main;
import br.sapiens.daos.AlunoDao;
import br.sapiens.daos.MatriculaDao;
import br.sapiens.models.Aluno;
import br.sapiens.models.CursosEnum;
import br.sapiens.models.DateParse;
import br.sapiens.models.Matricula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


public class MatriculaController {

    public Label id;

    MatriculaDao dao = new MatriculaDao();


    @FXML
    TableView table;

    public MatriculaController() throws SQLException {
    }

    @FXML
    public void initialize() throws Exception {

        if(table != null) {
            TableColumn<Matricula, String> nome = new TableColumn("Aluno");
            nome.setCellValueFactory(new PropertyValueFactory("aluno"));

            TableColumn<Matricula, String> disc = new TableColumn("Disciplina");
            disc.setCellValueFactory(new PropertyValueFactory("disciplina"));

            TableColumn<Matricula, String> perio = new TableColumn("Periodo");
            perio.setCellValueFactory(new PropertyValueFactory("periodo"));


            table.getColumns().addAll(List.of(nome,disc,perio));
            table.getItems().addAll(dao.findAll());
        }

    }

}
