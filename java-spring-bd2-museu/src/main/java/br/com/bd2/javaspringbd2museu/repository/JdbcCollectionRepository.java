package br.com.bd2.javaspringbd2museu.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.bd2.javaspringbd2museu.model._Collection;

@Repository
public class JdbcCollectionRepository implements CollectionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<_Collection> listHighestNumberOfLoans() {
        String query = "SELECT c.nome, c.descricao, c.tipo, c.telefone, c.endereco, c.pessoa_contato, count(*) as quantidade "+ 
            "FROM colecao c join emprestado e on e.nome_colecao = c.nome "+
            "GROUP BY c.nome ORDER BY quantidade DESC";
        System.out.println("query "+query);
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(_Collection.class));
    }

    public List<_Collection> listHighestNumberOfLoansByMonthAndYear(String month, String year) {
        String query = "SELECT c.nome, descricao, tipo, telefone, endereco, pessoa_contato, count(*) as quantidade "+ 
            "FROM colecao c join emprestado e on e.nome_colecao = c.nome "+
            "WHERE DATE_PART('YEAR', e.data_emprestimo) = '"+year+"' "+
            "AND DATE_PART('MONTH', e.data_emprestimo) = '"+month+"' "+
            "GROUP BY c.nome ORDER BY quantidade DESC";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(_Collection.class));
    }

    public List<_Collection> listHighestNumberOfLoansByMonth(String month) {
        String query = "SELECT c.nome, descricao, tipo, telefone, endereco, pessoa_contato, count(*) as quantidade "+ 
            "FROM colecao c join emprestado e on e.nome_colecao = c.nome "+
            "WHERE DATE_PART('MONTH', e.data_emprestimo) = '"+month+"' "+
            "GROUP BY c.nome ORDER BY quantidade DESC";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(_Collection.class));
    }

    public List<_Collection> listHighestNumberOfLoansByYear(String year) {
        String query = "SELECT c.nome, descricao, tipo, telefone, endereco, pessoa_contato, count(*) as quantidade "+ 
            "FROM colecao c join emprestado e on e.nome_colecao = c.nome "+
            "WHERE DATE_PART('YEAR', e.data_emprestimo) = '"+year+"' "+
            "GROUP BY c.nome ORDER BY quantidade DESC";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(_Collection.class));
    }
}
