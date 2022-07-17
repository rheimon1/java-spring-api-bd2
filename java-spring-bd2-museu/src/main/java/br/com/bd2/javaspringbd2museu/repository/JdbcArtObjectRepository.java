package br.com.bd2.javaspringbd2museu.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.bd2.javaspringbd2museu.model.ArtObject;

@Repository
public class JdbcArtObjectRepository implements ArtObjectRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ArtObject> findAll() {
        return jdbcTemplate.query("SELECT * FROM objeto_arte", BeanPropertyRowMapper.newInstance(ArtObject.class));
    }

    public List<ArtObject> findByType(String type) {
        String query = "SELECT * FROM objeto_arte as obj WHERE tipo_objeto_arte = '" + type + "'";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ArtObject.class));
    }

    public List<ArtObject> findByClass(String _class) {
        String query = "SELECT * FROM objeto_arte as obj "+
            "JOIN museu." + _class + " classe on classe.o_numid = obj.numid ";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ArtObject.class));
    }

    public int getBorrowedCount() {
        String query = "SELECT count(*) FROM emprestado as emp "+
            "JOIN colecao as col on col.nome = emp.nome_colecao ";
        Number number =  jdbcTemplate.queryForObject(query, Integer.class);
        return (number != null ? number.intValue() : 0);
    }

    public int getBorrowedCountByCollectionName(String collectionName) {
        String query = "SELECT count(*) FROM emprestado as emp "+
            "JOIN colecao as col on col.nome = emp.nome_colecao "+
            "WHERE nome_colecao='"+collectionName+"'";
        Number number =  jdbcTemplate.queryForObject(query, Integer.class);
        return (number != null ? number.intValue() : 0);
    }

    public int getBorrowedCountByMonth(String month) {
        String query = "SELECT count(*) FROM emprestado as emp "+
            "JOIN colecao as col on col.nome = emp.nome_colecao "+
            "WHERE DATE_PART('MONTH', emp.data_emprestimo)='"+month+"'";
        Number number =  jdbcTemplate.queryForObject(query, Integer.class);
        return (number != null ? number.intValue() : 0);
    }

    public int getBorrowedCountByYear(String year) {
        String query = "SELECT count(*) FROM emprestado as emp "+
            "JOIN colecao as col on col.nome = emp.nome_colecao "+
            "WHERE DATE_PART('YEAR', emp.data_emprestimo)='"+year+"'";
        Number number =  jdbcTemplate.queryForObject(query, Integer.class);
        return (number != null ? number.intValue() : 0);
    }
}
