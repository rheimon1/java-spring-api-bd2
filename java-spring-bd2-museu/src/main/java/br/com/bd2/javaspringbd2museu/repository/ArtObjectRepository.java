package br.com.bd2.javaspringbd2museu.repository;

import java.util.List;

import br.com.bd2.javaspringbd2museu.model.ArtObject;

public interface ArtObjectRepository {
  List<ArtObject> findAll();
}
