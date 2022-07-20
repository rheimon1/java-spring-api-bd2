package br.com.bd2.javaspringbd2museu.repository;

import java.util.List;

import br.com.bd2.javaspringbd2museu.model.ArtObject;

public interface ArtObjectRepository {
  List<ArtObject> findAll();
  List<ArtObject> findByTypeAndClass(String type, String _class);
  List<ArtObject> findByType(String type);
  List<ArtObject> findByClass(String _class);
  List<ArtObject> getBorrowedCount();
  List<ArtObject> getBorrowedCountByCollectionNameAndMonthAndYear(String collectionName, String month, String year);
  List<ArtObject> getBorrowedCountByMonthAndYear(String month, String year);
  List<ArtObject> getBorrowedCountByCollectionNameAndMonth(String collectionName, String month);
  List<ArtObject> getBorrowedCountByCollectionNameAndYear(String collectionName, String year);
  List<ArtObject> getBorrowedCountByCollectionName(String collectionName);
  List<ArtObject> getBorrowedCountByMonth(String month);
  List<ArtObject> getBorrowedCountByYear(String year);
  List<ArtObject> findAllPermanentCollection();
  List<ArtObject> findPermanentCollectionByMonthAndYear(String month, String year);
  List<ArtObject> findByMonth(String month);
  List<ArtObject> findByYear(String year);
  int save(ArtObject artObject);
  List<ArtObject> findTitleAndCostOrderedByCost();
}
