package br.sapiens.configs;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriaEntidades {
    Connection conn = null;

    public CriaEntidades(Connection conn) throws SQLException {
     this.conn = conn;
     this.alunoEntidade();
     this.disciplinaEntidade();
     this.matriculaEntidade();
    }

    private void alunoEntidade() throws SQLException {
        String aluno = "CREATE TABLE `Aluno` (\n" +
                "  `id` bigint auto_increment,\n" +
                "  `nome` varchar(200),\n" +
                "  `dataNascimento` Date,\n" +
                "  `curso` varchar(200),\n" +
                "  PRIMARY KEY (`id`)\n" +
                ");\n";

        Statement statement = conn.createStatement();
        statement.execute(aluno);
        statement.close();
        System.out.println("Criado aluno com sucesso");
    }

    private void disciplinaEntidade() throws SQLException {
        String disciplina = "CREATE TABLE `Disciplina` (\n" +
                "  `id` bigint auto_increment,\n" +
                "  `descricao` varchar(200),\n" +
                "  `curso` varchar(200),\n" +
                "  PRIMARY KEY (`id`)\n" +
                ");\n" +
                "\n";

        Statement statement = conn.createStatement();
        statement.execute(disciplina);
        statement.close();
        System.out.println("Criado Disciplina com sucesso");

    }

    private void matriculaEntidade() throws SQLException {
        String matricula = "" +
                "CREATE TABLE `Matricula` (\n" +
                "  `disciplina` int,\n" +
                "  `aluno` int,\n" +
                "  `periodo` varchar(200),\n" +
                "  PRIMARY KEY (`periodo`,`disciplina`, `aluno`)\n" +
                ");\n";

        Statement statement = conn.createStatement();
        statement.execute(matricula);
        statement.close();
        System.out.println("Criado Matricula com sucesso");

    }

}