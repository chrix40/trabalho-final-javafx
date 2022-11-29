package br.sapiens.controllers;

import br.sapiens.daos.DisciplinaDao;
//import br.sapiens.daos.MatriculaDao;
import br.sapiens.daos.MatriculaDao;
import br.sapiens.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class VinculaAlunoDisciplina extends AlunoController{

    private Aluno aluno;

    DisciplinaDao dao = new DisciplinaDao();
    MatriculaDao daoMatricula = new MatriculaDao();

    @FXML
    Label idLabel;

    @FXML
    Label descricaoLabel;

    @FXML
    ChoiceBox vinculo;

    @FXML
    ChoiceBox Periodo;

    @FXML
    TableView table_matricula;


    public VinculaAlunoDisciplina() throws Exception {
    }


    public void recebeAluno(Aluno aluno){
        this.aluno = aluno;
        idLabel.setText(aluno.getId().toString());
        descricaoLabel.setText(aluno.getNome());
        carregaMatriculas();
    }

    @FXML
    public void initialize() throws IOException, SQLException {
        ObservableList<Disciplina> list = FXCollections.observableArrayList();
        list.addAll(dao.findAll());
        System.out.println(list);
        vinculo.setItems(list);

        System.out.println(vinculo.getClass().toString());

        ObservableList<PeriodoEnum> listP = FXCollections.observableArrayList();
        listP.addAll(PeriodoEnum.values());
        Periodo.setItems(listP);
        if (table != null){
            TableColumn<Aluno, String> nome = new TableColumn("Nome");
            nome.setCellValueFactory(new PropertyValueFactory("Nome"));
        }

        if(table_matricula != null) {
            //TableColumn<Aluno, String> nome = new TableColumn("Nome");
            //nome.setCellValueFactory(new PropertyValueFactory("nome"));

            TableColumn<Matricula, String> disc = new TableColumn("Disciplina");
            disc.setCellValueFactory(new PropertyValueFactory("disciplina"));

            TableColumn<Matricula, String> perio = new TableColumn("Periodo");
            perio.setCellValueFactory(new PropertyValueFactory("periodo"));


            table_matricula.getColumns().addAll(List.of(nome,disc,perio));
            table_matricula.getItems().addAll(dao.findAll());
        }
        //ObservableList<Matricula> matriculas_list = FXCollections.observableArrayList();
        //matriculas_list.addAll(daoMatricula.findAll());
        //Periodo.setItems(matriculas_list);
    }


    public void salvar() throws SQLException {

        String id = this.idLabel.getText();
        Integer idInt = null;
        if (!id.isEmpty())
            idInt = Integer.valueOf(id);
        ObservableList<Disciplina> list = FXCollections.observableArrayList();
        list.addAll(dao.findAll());
        Optional<Disciplina> disc = list.stream().findAny(); // percorre a lista e pega o primeiro
        try {
            Matricula retorno = daoMatricula.save(new Matricula(aluno, disc.get(), (PeriodoEnum) Periodo.getValue()));
            System.out.println("\nAluno: "  + retorno.getAluno().getNome() + "\nDisciplina:" + retorno.getDisciplina() +  "\nCurso: "  +retorno.getDisciplina().getCurso() +  " \n"  + retorno.getPeriodo().toString());
        } catch (Exception e) {
            System.out.println(" :( Erro ao cadastrar");
            System.out.println(e);
        }

//        for (int i = 0; i < list.size(); i++){
//            if(list.get(i).getDisciplina() == disciplina_escolhida){
////                Matricula retorno = daoMatricula.save(new Matricula(aluno, list.get(i),(PeriodoEnum)Periodo.getValue()));
//                System.out.println("Deu Bom...");
//                System.out.println(retorno.getAluno().getCurso().toString());
//                return;
//            }
//        }
    }

    public void carregaMatriculas(){
        //método...
    }

}
