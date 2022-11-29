package br.sapiens.models;

import br.sapiens.daos.DisciplinaDao;

public class Disciplina   {

    private Integer id;
    private String disciplina;
    private CursosEnum curso;

    public Disciplina(Integer id,String disciplina, CursosEnum curso) throws Exception {
        this.id = id;
        this.disciplina = disciplina;
        if(disciplina.isEmpty()){
            throw new Exception("Não é possível inserir uma descricão vazio");
        }
        this.curso = curso;

    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public void setDisciplina(String descricao) { this.disciplina = descricao;}
    public String getDisciplina(){
        return disciplina;
    }

    public String toString(){
        return disciplina;
    }



    public void setCurso(CursosEnum curso){this.curso = curso;}
    public CursosEnum getCurso(){return curso;}




}
