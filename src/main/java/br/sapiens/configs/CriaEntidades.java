package br.sapiens.configs;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriaEntidades {
    
    public void initialize() throws SQLException {
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
