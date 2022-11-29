package br.sapiens.daos;

import br.sapiens.configs.ConnectionSingleton;
import br.sapiens.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MatriculaDao implements CrudRepository<Matricula, Integer> {

    private final Connection conn;

    DisciplinaDao daoDisciplina = new DisciplinaDao();
    AlunoDao daoAluno = new AlunoDao();

    public MatriculaDao () throws SQLException {
        this.conn = new ConnectionSingleton().getConn();
    }
    @Override
    public <S extends Matricula> S save(S entity) throws SQLException {
//        Optional<Matricula> m = this.findById(entity.getId());
        if(entity.getId() == null)
            return insertInto(entity);
        else
            return update(entity);
    }

    private <S extends Matricula> S update(S entity) throws SQLException {
        String sql = "UPDATE Matricula SET aluno = ?, disciplina = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setObject(1,entity.getDisciplina().getId());
        pstmt.setObject(2,entity.getAluno().getId());
        pstmt.setString(3,entity.getPeriodo().toString());
        pstmt.setString(4,entity.getId().toString());
        pstmt.executeUpdate();
        return entity;
    }

    private <S extends Matricula> S insertInto(S entity) throws SQLException {
        String sql = "Insert into Matricula(disciplina, aluno,periodo) values(?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setObject(1,entity.getAluno().getId());
        pstmt.setObject(2,entity.getDisciplina().getId());
        pstmt.setString(3,entity.getPeriodo().toString());
        int affectedRows = pstmt.executeUpdate();
        if (affectedRows == 0)
            throw new SQLException("Falha, nenhuma linha foi inserida");
        ResultSet generatedKeys = pstmt.getGeneratedKeys();
        generatedKeys.next();
        entity.setId(generatedKeys.getInt(1));
        return entity;
    }


    @Override
    public <S extends Matricula> Iterable<Matricula> saveAll(Iterable<S> entities) throws SQLException {
        return null;
    }

    @Override
    public Optional<Matricula> findById(Integer integer) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Matricula> findAllById(Iterable<Integer> integers) throws SQLException {
        String sql = "select * from Matricula ";
        if(integers != null) {
            List<Integer> lista = new ArrayList();
            Iterator<Integer> interetor = integers.iterator();
            while(interetor.hasNext()){
                lista.add(interetor.next());
            }
            String sqlIN = lista.stream()
                    .map(x -> String.valueOf(x))
                    .collect(Collectors.joining(",", "(", ")"));
            sql = sql+" where id in(?)".replace("(?)", sqlIN);
        }
        PreparedStatement stmt = conn.prepareStatement(sql);
        List<Matricula> resultado = new ArrayList();
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int aluno = rs.getInt(1);
                int disciplina = rs.getInt(2);
                PeriodoEnum periodo = PeriodoEnum.valueOf(rs.getString(3));
                Optional<Aluno> al = daoAluno.findById(aluno);
                Optional<Disciplina> di = daoDisciplina.findById(disciplina);
                if(al.isPresent() && di.isPresent()){
                    Aluno aluno_ = al.get();
                    Disciplina disciplina_ = di.get();
                    Matricula matricula = new Matricula(aluno_, disciplina_, periodo);
                    resultado.add(matricula);
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    @Override
    public void delete(Matricula entity) throws SQLException {

    }

    @Override
    public void deleteById(Integer integer) throws SQLException {

    }

    @Override
    public void deleteAll(Iterable<? extends Matricula> entities) {

    }

    public List<Matricula> findAll() throws SQLException {
        return findAllById(null);
    }
}
