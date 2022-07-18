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
            "JOIN " + _class + " classe on classe.o_numid = obj.numid ";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ArtObject.class));
    }

    public List<ArtObject> findByTypeAndClass(String type, String _class) {
        String query = "SELECT * FROM objeto_arte as obj "+
            "JOIN " + _class + " classe on classe.o_numid = obj.numid "+
            "WHERE tipo_objeto_arte = '" + type + "'";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ArtObject.class));
    }

    public int getBorrowedCount() {
        String query = "SELECT count(*) FROM emprestado as emp "+
            "JOIN colecao as col on col.nome = emp.nome_colecao ";
        Number number =  jdbcTemplate.queryForObject(query, Integer.class);
        return (number != null ? number.intValue() : 0);
    }

    public int getBorrowedCountByCollectionNameAndMonthAndYear(String collectionName, String month, String year) {
        String query = "SELECT count(*) FROM emprestado as emp "+
            "JOIN colecao as col on col.nome = emp.nome_colecao "+
            "WHERE nome_colecao='"+collectionName+"' "+
            "AND DATE_PART('YEAR', emp.data_emprestimo) = '"+year+"' "+
            "AND DATE_PART('MONTH', emp.data_emprestimo) = '"+month+"'";
        Number number =  jdbcTemplate.queryForObject(query, Integer.class);
        return (number != null ? number.intValue() : 0);
    }

    public int getBorrowedCountByMonthAndYear(String month, String year) {
        String query = "SELECT count(*) FROM emprestado as emp "+
            "JOIN colecao as col on col.nome = emp.nome_colecao "+
            "WHERE DATE_PART('YEAR', emp.data_emprestimo) = '"+year+"' "+
            "AND DATE_PART('MONTH', emp.data_emprestimo) = '"+month+"'";
        Number number =  jdbcTemplate.queryForObject(query, Integer.class);
        return (number != null ? number.intValue() : 0);
    }

    public int getBorrowedCountByCollectionNameAndMonth(String collectionName, String month) {
        String query = "SELECT count(*) FROM emprestado as emp "+
            "JOIN colecao as col on col.nome = emp.nome_colecao "+
            "WHERE nome_colecao='"+collectionName+"' "+
            "AND DATE_PART('MONTH', emp.data_emprestimo) = '"+month+"'";
        Number number =  jdbcTemplate.queryForObject(query, Integer.class);
        return (number != null ? number.intValue() : 0);
    }

    public int getBorrowedCountByCollectionNameAndYear(String collectionName, String year) {
        String query = "SELECT count(*) FROM emprestado as emp "+
            "JOIN colecao as col on col.nome = emp.nome_colecao "+
            "WHERE nome_colecao='"+collectionName+"' "+
            "AND DATE_PART('YEAR', emp.data_emprestimo) = '"+year+"'";
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

    public List<ArtObject> findAllPermanentCollection() {
        String query = "SELECT titulo, tipo_objeto_arte, custo, estilo, c.data_aquisicao "+
            "FROM museu.objeto_arte as obj "+
            "JOIN museu.colecao_permanente c on c.o_numid = obj.numid";
        return jdbcTemplate.query(
            query, 
            BeanPropertyRowMapper.newInstance(ArtObject.class)
        );
    }

    public List<ArtObject> findPermanentCollectionByMonthAndYear(String month, String year) {
        String query = "SELECT titulo, tipo_objeto_arte, custo, estilo, c.data_aquisicao "+
            "FROM museu.objeto_arte as obj "+
            "JOIN museu.colecao_permanente c on c.o_numid = obj.numid"+
            "WHERE DATE_PART('YEAR', c.data_aquisicao) = '"+year+"' "+
            "AND DATE_PART('MONTH', c.data_aquisicao) = '"+month;
        return jdbcTemplate.query(
            query, 
            BeanPropertyRowMapper.newInstance(ArtObject.class)
        );
    }

    public List<ArtObject> findByMonth(String month) {
        String query = "SELECT titulo, tipo_objeto_arte, custo, estilo, c.data_aquisicao "+
            "FROM museu.objeto_arte as obj "+
            "JOIN museu.colecao_permanente c on c.o_numid = obj.numid"+
            "WHERE DATE_PART('MONTH', c.data_aquisicao) = '"+month;
        return jdbcTemplate.query(
            query, 
            BeanPropertyRowMapper.newInstance(ArtObject.class)
        );
    }

    public List<ArtObject> findByYear(String year) {
        String query = "SELECT titulo, tipo_objeto_arte, custo, estilo, c.data_aquisicao "+
            "FROM museu.objeto_arte as obj "+
            "JOIN museu.colecao_permanente c on c.o_numid = obj.numid"+
            "WHERE DATE_PART('YEAR', c.data_aquisicao) = '"+year;
        return jdbcTemplate.query(
            query, 
            BeanPropertyRowMapper.newInstance(ArtObject.class)
        );
    }

    public int save(ArtObject artObject) {
        String query = "INSERT INTO objeto_arte (nome_artista, periodo, ano, titulo, descricao, cultura, estilo, custo, tipo_objeto_arte) VALUES(?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(query, new Object[] { artObject.getNomeArtista(), artObject.getPeriodo(), artObject.getAno(), artObject.getTitulo(),artObject.getDescricao(), artObject.getCultura(), artObject.getEstilo(), artObject.getCusto(), artObject.getTipo() });
    }
}
