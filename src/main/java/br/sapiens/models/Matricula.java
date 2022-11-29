package br.sapiens.models;

public class Matricula {
    private Aluno aluno;
    private Disciplina disciplina;
    private Integer id;
    private PeriodoEnum periodo;


    public Matricula(Aluno aluno, Disciplina disciplina, PeriodoEnum periodo) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.periodo = periodo;
    }

    /*public void getAluno(Aluno aluno) {
        this.aluno = aluno;
        aluno.toString();
    }*/

    public Aluno getAluno() {
        return aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public Integer getId() {
        return id;
    }

    public PeriodoEnum getPeriodo() {
        return periodo;
    }

    public void setId(int anInt) {
    }
}
