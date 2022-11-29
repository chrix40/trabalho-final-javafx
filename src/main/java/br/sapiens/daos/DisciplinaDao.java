package br.sapiens.daos;

import br.sapiens.configs.ConnectionSingleton;
import br.sapiens.models.Aluno;
import br.sapiens.models.CursosEnum;
import br.sapiens.models.Disciplina;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DisciplinaDao implements CrudRepository<Disciplina,Integer> {

    private final Connection conn;

    public DisciplinaDao () throws SQLException {
        this.conn = new ConnectionSingleton().getConn();
    }

    @Override
    public <S extends Disciplina> S save(S entity) throws SQLException {
        if(entity.getId() == null)
            return insertInto(entity);
        else
            return update(entity);
    }

    @Override
    public <S extends Disciplina> Iterable<Disciplina> saveAll(Iterable<S> entities) throws SQLException {
        ArrayList lista = new ArrayList();
        for(S entity : entities) {
            lista.add(save(entity));
        }
        return lista;
    }

    private <S extends Disciplina> S insertInto(S entity) throws SQLException {
        String sql = "Insert into Disciplina(descricao ,curso) values(?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1,entity.getDisciplina());
        pstmt.setString(2,entity.getCurso().toString());
        int affectedRows = pstmt.executeUpdate();
        if (affectedRows == 0)
            throw new SQLException("Falha, nenhuma linha foi inserida");
        ResultSet generatedKeys = pstmt.getGeneratedKeys();
        generatedKeys.next();
        entity.setId(generatedKeys.getInt(1));
        return entity;
    }

    private <S extends Disciplina> S update(S entity) throws SQLException {
        throw new SQLException("Não é possível editar a disciplina");
    }


    @Override
    public Optional<Disciplina> findById(Integer id) throws SQLException {
        List<Disciplina> resultados = (List<Disciplina>) findAllById(List.of(id));

        if(resultados == null || resultados.size() != 1)
            throw new SQLException("Erro ao buscar valores, não existe somente um resultado! Size "+resultados.size());
        return Optional.ofNullable(resultados.get(0));
    }

    @Override
    public Iterable<Disciplina> findAllById(Iterable<Integer> ids) throws SQLException {
        String sql = "select * from Disciplina ";
        if(ids != null) {
            List<Integer> lista = new ArrayList();
            Iterator<Integer> interetor = ids.iterator();
            while(interetor.hasNext()){
                lista.add(interetor.next());
            }
            String sqlIN = lista.stream()
                    .map(x -> String.valueOf(x))
                    .collect(Collectors.joining(",", "(", ")"));
            sql = sql+" where id in(?)".replace("(?)", sqlIN);
        }
        PreparedStatement stmt = conn.prepareStatement(sql);
        List<Disciplina> resultado = new ArrayList();
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt(1);
                String descricao = rs.getString(2);
                CursosEnum curso = CursosEnum.valueOf(rs.getString(3));
                Disciplina disciplina = new Disciplina(id, descricao, curso);
                resultado.add(disciplina);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }

    @Override
    public void delete(Disciplina entity) throws SQLException {
        String sql = "DELETE FROM Disciplina";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
    }

    @Override
    public void deleteById(Integer integer) throws SQLException {
        String sql = "DELETE FROM Disciplina WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, String.valueOf(integer));
        pstmt.executeUpdate();
    }

    @Override
    public void deleteAll(Iterable<? extends Disciplina> entities) {

        Iterator<? extends Disciplina> disciplinas = entities.iterator();
        while(disciplinas.hasNext()){
            try {
                deleteById(disciplinas.next().getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public List<Disciplina> findAll() throws SQLException {
        return (List<Disciplina>) findAllById(null);
    }
}
