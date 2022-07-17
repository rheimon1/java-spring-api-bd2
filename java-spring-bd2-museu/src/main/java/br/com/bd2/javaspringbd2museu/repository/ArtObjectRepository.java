package br.com.bd2.javaspringbd2museu.repository;

import java.util.List;

import br.com.bd2.javaspringbd2museu.model.ArtObject;

public interface ArtObjectRepository {
  List<ArtObject> findAll();
  List<ArtObject> findByType(String type);
  List<ArtObject> findByClass(String _class);
  int getBorrowedCount();
  int getBorrowedCountByCollectionName(String collectionName);
  int getBorrowedCountByMonth(String month);
  int getBorrowedCountByYear(String year);
  
}
