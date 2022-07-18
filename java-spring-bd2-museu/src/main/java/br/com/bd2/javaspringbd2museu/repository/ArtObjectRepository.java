package br.com.bd2.javaspringbd2museu.repository;

import java.util.List;

import br.com.bd2.javaspringbd2museu.model.ArtObject;

public interface ArtObjectRepository {
  List<ArtObject> findAll();
  List<ArtObject> findByTypeAndClass(String type, String _class);
  List<ArtObject> findByType(String type);
  List<ArtObject> findByClass(String _class);
  int getBorrowedCount();
  int getBorrowedCountByCollectionNameAndMonthAndYear(String collectionName, String month, String year);
  int getBorrowedCountByMonthAndYear(String month, String year);
  int getBorrowedCountByCollectionNameAndMonth(String collectionName, String month);
  int getBorrowedCountByCollectionNameAndYear(String collectionName, String year);
  int getBorrowedCountByCollectionName(String collectionName);
  int getBorrowedCountByMonth(String month);
  int getBorrowedCountByYear(String year);
  List<ArtObject> findAllPermanentCollection();
  List<ArtObject> findPermanentCollectionByMonthAndYear(String month, String year);
  List<ArtObject> findByMonth(String month);
  List<ArtObject> findByYear(String year);
  int save(ArtObject artObject);
}
