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
}
