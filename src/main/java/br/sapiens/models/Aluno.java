package br.sapiens.models;

import br.sapiens.daos.AlunoDao;

import java.util.Date;

public class Aluno extends AlunoDao {

    private Integer id;
    private String nome;
    private Date data_nascimento;
    private CursosEnum curso;


    public Aluno(Integer id , String nome, Date data_nascimento, CursosEnum curso) throws Exception {
        this.id = id;
        this.nome = nome;
        this.data_nascimento = data_nascimento;

        if(nome.isEmpty()){
            throw new Exception("Nome do aluno não pode ser vazio");
        }
        this.curso = curso;

    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }


    public void setNom(String nome){this.nome = nome;}
    public String getNome(){
        return nome;
    }

    public void setDataNascimento(Date data_nascimento){this.data_nascimento = data_nascimento;}
    public Date getData_nascimento(){return data_nascimento; }

    public void setCurso(CursosEnum curso){this.curso = curso;}
    public CursosEnum getCurso(){
        return curso;
    }

    public Date getSqlDate() {

        return new java.sql.Date(data_nascimento.getTime());
    }

}
