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
        return jdbcTemplate.query("SELECT *, tipo_objeto_arte as tipo FROM objeto_arte", BeanPropertyRowMapper.newInstance(ArtObject.class));
    }

    public List<ArtObject> findByType(String type) {
        String query = "SELECT *, tipo_objeto_arte as tipo FROM objeto_arte as obj WHERE tipo_objeto_arte = '" + type + "'";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ArtObject.class));
    }

    public List<ArtObject> findByClass(String _class) {
        String query = "SELECT *, tipo_objeto_arte as tipo FROM objeto_arte as obj "+
            "JOIN " + _class + " classe on classe.o_numid = obj.numid ";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ArtObject.class));
    }

    public List<ArtObject> findByTypeAndClass(String type, String _class) {
        String query = "SELECT *, tipo_objeto_arte as tipo FROM objeto_arte as obj "+
            "JOIN " + _class + " classe on classe.o_numid = obj.numid "+
            "WHERE tipo_objeto_arte = '" + type + "'";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ArtObject.class));
    }

    public List<ArtObject> getBorrowedCount() {
        String query = "SELECT nome_colecao as nomeColecao, count(*) quantidade FROM emprestado as emp "+
            "JOIN colecao as col on col.nome = emp.nome_colecao "+
            "GROUP BY nome_colecao LIMIT 5";
        System.out.println("teste "+query);
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ArtObject.class));
    }

    public List<ArtObject> getBorrowedCountByCollectionNameAndMonthAndYear(String collectionName, String month, String year) {
        String query = "SELECT nome_colecao as nome_colecao, count(*) as quantidade FROM emprestado as emp "+
            "JOIN colecao as col on col.nome = emp.nome_colecao "+
            "WHERE nome_colecao='"+collectionName+"' "+
            "AND DATE_PART('YEAR', emp.data_emprestimo) = '"+year+"' "+
            "AND DATE_PART('MONTH', emp.data_emprestimo) = '"+month+"' "+
            "GROUP BY nome_colecao LIMIT 5";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ArtObject.class));
    }

    public List<ArtObject> getBorrowedCountByMonthAndYear(String month, String year) {
        String query = "SELECT nome_colecao as nome_colecao, count(*) as quantidade FROM emprestado as emp "+
            "JOIN colecao as col on col.nome = emp.nome_colecao "+
            "WHERE DATE_PART('YEAR', emp.data_emprestimo) = '"+year+"' "+
            "AND DATE_PART('MONTH', emp.data_emprestimo) = '"+month+"' "+
            "GROUP BY nome_colecao LIMIT 5";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ArtObject.class));
    }

    public List<ArtObject> getBorrowedCountByCollectionNameAndMonth(String collectionName, String month) {
        String query = "SELECT nome_colecao as nome_colecao, count(*) as quantidade FROM emprestado as emp "+
            "JOIN colecao as col on col.nome = emp.nome_colecao "+
            "WHERE nome_colecao='"+collectionName+"' "+
            "AND DATE_PART('MONTH', emp.data_emprestimo) = '"+month+"' "+
            "GROUP BY nome_colecao LIMIT 5";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ArtObject.class));
    }

    public List<ArtObject> getBorrowedCountByCollectionNameAndYear(String collectionName, String year) {
        String query = "SELECT nome_colecao as nome_colecao, count(*) as quantidade FROM emprestado as emp "+
            "JOIN colecao as col on col.nome = emp.nome_colecao "+
            "WHERE nome_colecao='"+collectionName+"' "+
            "AND DATE_PART('YEAR', emp.data_emprestimo) = '"+year+"' "+
            "GROUP BY nome_colecao LIMIT 5";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ArtObject.class));
    }

    public List<ArtObject> getBorrowedCountByCollectionName(String collectionName) {
        String query = "SELECT nome_colecao as nome_colecao, count(*) as quantidade FROM emprestado as emp "+
            "JOIN colecao as col on col.nome = emp.nome_colecao "+
            "WHERE nome_colecao='"+collectionName+"' "+
            "GROUP BY nome_colecao LIMIT 5";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ArtObject.class));
    }

    public List<ArtObject> getBorrowedCountByMonth(String month) {
        String query = "SELECT nome_colecao as nome_colecao, count(*) as quantidade FROM emprestado as emp "+
            "JOIN colecao as col on col.nome = emp.nome_colecao "+
            "WHERE DATE_PART('MONTH', emp.data_emprestimo)='"+month+"' "+
            "GROUP BY nome_colecao LIMIT 5";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ArtObject.class));
    }

    public List<ArtObject> getBorrowedCountByYear(String year) {
        String query = "SELECT nome_colecao as nome_colecao, count(*) as quantidade FROM emprestado as emp "+
            "JOIN colecao as col on col.nome = emp.nome_colecao "+
            "WHERE DATE_PART('YEAR', emp.data_emprestimo)='"+year+"' "+
            "GROUP BY nome_colecao LIMIT 5";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ArtObject.class));
    }

    public List<ArtObject> findAllPermanentCollection() {
        String query = "SELECT titulo, tipo_objeto_arte as tipo, custo, estilo, to_char(c.data_aquisicao, 'DD/MM/YYYY') as dataCompra "+
            "FROM museu.objeto_arte as obj "+
            "JOIN museu.colecao_permanente c on c.o_numid = obj.numid";
        return jdbcTemplate.query(
            query,
            BeanPropertyRowMapper.newInstance(ArtObject.class)
        );
    }

    public List<ArtObject> findPermanentCollectionByMonthAndYear(String month, String year) {
        String query = "SELECT titulo, tipo_objeto_arte as tipo, custo, estilo, to_char(c.data_aquisicao, 'DD/MM/YYYY') as dataCompra "+
            "FROM museu.objeto_arte as obj "+
            "JOIN museu.colecao_permanente c on c.o_numid = obj.numid "+
            "WHERE DATE_PART('YEAR', c.data_aquisicao) = '"+year+"' "+
            "AND DATE_PART('MONTH', c.data_aquisicao) = '"+month+"'";
        System.out.println("query "+query);
        return jdbcTemplate.query(
            query,
            BeanPropertyRowMapper.newInstance(ArtObject.class)
        );
    }

    public List<ArtObject> findByMonth(String month) {
        String query = "SELECT titulo, tipo_objeto_arte as tipo, custo, estilo, to_char(c.data_aquisicao, 'DD/MM/YYYY') as dataCompra "+
            "FROM museu.objeto_arte as obj "+
            "JOIN museu.colecao_permanente c on c.o_numid = obj.numid "+
            "WHERE DATE_PART('MONTH', c.data_aquisicao) = '"+month+"'";
        System.out.println("query "+query);
        return jdbcTemplate.query(
            query,
            BeanPropertyRowMapper.newInstance(ArtObject.class)
        );
    }

    public List<ArtObject> findByYear(String year) {
        String query = "SELECT titulo, tipo_objeto_arte as tipo, custo, estilo, to_char(c.data_aquisicao, 'DD/MM/YYYY') as dataCompra "+
            "FROM museu.objeto_arte as obj "+
            "JOIN museu.colecao_permanente c on c.o_numid = obj.numid "+
            "WHERE DATE_PART('YEAR', c.data_aquisicao) = '"+year+"'";
        return jdbcTemplate.query(
            query,
            BeanPropertyRowMapper.newInstance(ArtObject.class)
        );
    }

    public int save(ArtObject artObject) {
        try {
            String query = "INSERT INTO objeto_arte (nome_artista, periodo, ano, titulo, descricao, cultura, estilo, custo, tipo_objeto_arte) VALUES(?,?,?,?,?,?,?,?,?)";
            return jdbcTemplate.update(query, new Object[] { artObject.getNomeArtista(), artObject.getPeriodo(), artObject.getAno(), artObject.getTitulo(),artObject.getDescricao(), artObject.getCultura(), artObject.getEstilo(), artObject.getCusto(), artObject.getTipo() });
        } catch (Exception e) {
            System.out.println(e);
            return 1;
        }
    }

    public List<ArtObject> findTitleAndCostOrderedByCost() {
        String query = "SELECT titulo, custo FROM objeto_arte ORDER BY custo";
        return jdbcTemplate.query(
            query,
            BeanPropertyRowMapper.newInstance(ArtObject.class)
        );
    }
}
