package br.com.bd2.javaspringbd2museu.repository;

import java.util.List;

import br.com.bd2.javaspringbd2museu.model._Collection;

public interface CollectionRepository {
    List<_Collection> listHighestNumberOfLoans();
    List<_Collection> listHighestNumberOfLoansByMonthAndYear(String month, String year);
    List<_Collection> listHighestNumberOfLoansByMonth(String month);
    List<_Collection> listHighestNumberOfLoansByYear(String year);
}
