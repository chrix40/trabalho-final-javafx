package br.sapiens.controllers;

import br.sapiens.Main;
import br.sapiens.daos.DisciplinaDao;
import br.sapiens.models.Aluno;
import br.sapiens.models.CursosEnum;
import br.sapiens.models.DateParse;
import br.sapiens.models.Disciplina;
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
import java.util.Date;
import java.util.List;

public class DisciplinaController {

    public Label id;

    DisciplinaDao dao = new DisciplinaDao();

    @FXML
    TextField descricao;

    @FXML
    ChoiceBox curso;

    @FXML
    Pane painelVinculo;
    @FXML
    TableView table;



    public DisciplinaController() throws SQLException {}

    @FXML
    public void initialize() throws Exception {
        if(curso != null){ // estou na tela de cadastro
            ObservableList<CursosEnum> list = FXCollections.observableArrayList();
            list.addAll(CursosEnum.values());
            curso.setItems(list);

        }

        if(table != null){ // estou na tela de listagem
            TableColumn<Disciplina, String> idC = new TableColumn("Id");
            idC.setCellValueFactory(new PropertyValueFactory("id"));

            TableColumn<Disciplina, String> descricao = new TableColumn("Descrição");
            descricao.setCellValueFactory(new PropertyValueFactory("disciplina"));

            TableColumn<Disciplina, String> cursos = new TableColumn("Curso");
            cursos.setCellValueFactory(new PropertyValueFactory("curso"));




            table.getColumns().addAll(List.of(idC,descricao,cursos));
            table.getItems().addAll(dao.findAll());
        }

    }




    public void salvar() throws Exception {

        String id = this.id.getText();
        Integer idInt = null;
        if(!id.isEmpty())
            idInt = Integer.valueOf(id);
        Disciplina retorno = dao.save(new Disciplina(idInt, descricao.getText(), (CursosEnum) curso.getValue()));
        this.id.setText(retorno.getId().toString());
    }




}
