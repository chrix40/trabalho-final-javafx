package br.sapiens.controllers;

import br.sapiens.Main;
import br.sapiens.daos.AlunoDao;
import br.sapiens.models.Aluno;
import br.sapiens.models.CursosEnum;
import br.sapiens.models.DateParse;
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

public class AlunoController {

    public Label id;

    AlunoDao dao = new AlunoDao();

    @FXML
    TextField nome;

    @FXML
    DatePicker dataNascimento;

    @FXML
    ChoiceBox curso;

    @FXML
    Pane painelVinculo;
    @FXML
    TableView table;


    public AlunoController() throws SQLException {
    }


    @FXML
    public void initialize() throws Exception {
        if(curso != null){ // estou na tela de cadastro
            ObservableList<CursosEnum> list = FXCollections.observableArrayList();
            list.addAll(CursosEnum.values());
            curso.setItems(list);

        }

        if(table != null){ // estou na tela de listagem
            TableColumn<Aluno, String> idC = new TableColumn("Id");
            idC.setCellValueFactory(new PropertyValueFactory("id"));

            TableColumn<Aluno, String> nome = new TableColumn("Nome");
            nome.setCellValueFactory(new PropertyValueFactory("Nome"));

            TableColumn<Aluno, String> data = new TableColumn("Data Nascimento");
            data.setCellValueFactory(new PropertyValueFactory("data_nascimento"));

            TableColumn<Aluno, String> curso = new TableColumn("Curso");
            curso.setCellValueFactory(new PropertyValueFactory("curso"));

            TableColumn action = new TableColumn("Ação");
            action.setCellFactory(criaAcao());


            table.getColumns().addAll(List.of(idC,nome,data,curso,action));
            table.getItems().addAll(dao.findAll());
        }

    }

    private Callback<TableColumn<Aluno, String>, TableCell<Aluno, String>> criaAcao() {
        return
                new Callback<TableColumn<Aluno, String>, TableCell<Aluno, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Aluno, String> param) {
                        final TableCell<Aluno, String> cell = new TableCell<Aluno, String>() {
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                setGraphic(null);
                                setText(null);
                                if (!empty) {
                                    Button btn = new Button("Vincular");
                                    btn.setOnAction(event -> {
                                        FXMLLoader fxmlLoader =
                                                new FXMLLoader(Main.class.getResource("/aluno/vinculo.fxml"));
                                        try {
                                            painelVinculo.getChildren().add(fxmlLoader.load());
                                            VinculaAlunoDisciplina controller = fxmlLoader.getController();
                                            Aluno aluno = this.getTableRow().getItem();
                                            controller.recebeAluno(aluno);
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    });
                                    setGraphic(btn);
                                }
                            }
                        };
                        return cell;
                    }
                };
    }


    public void salvar() throws Exception {

        String id = this.id.getText();
        Integer idInt = null;
        if(!id.isEmpty())
            idInt = Integer.valueOf(id);
        LocalDate localDate = dataNascimento.getValue();
        Date date = new DateParse().parse(localDate);
        Aluno retorno = dao.save(new Aluno(idInt, nome.getText(),date , (CursosEnum) curso.getValue()));
        this.id.setText(retorno.getId().toString());
    }










}
