package br.com.bd2.javaspringbd2museu.controllers;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bd2.javaspringbd2museu.model._Collection;
import br.com.bd2.javaspringbd2museu.repository.CollectionRepository;

/**
 * CollectionController
 */
@RestController
@RequestMapping("api")
public class CollectionController {
    @Autowired
    CollectionRepository collectionRepository;

	@GetMapping("/collection")
	public ResponseEntity<List<_Collection>> getAllCollections(
        @RequestParam(required = false) String month, 
        @RequestParam(required = false) String year
    ) {
        try {
            List<_Collection> collections = new ArrayList<_Collection>();
            if (month == null  && year == null) {
                this.collectionRepository.listHighestNumberOfLoans().forEach(collections::add);
            } else if (month != null  && year != null) {
                this.collectionRepository.listHighestNumberOfLoansByMonthAndYear(month, year).forEach(collections::add);
            } else if (month != null) {
                this.collectionRepository.listHighestNumberOfLoansByMonth(month).forEach(collections::add);
            } else if (year != null) {
                this.collectionRepository.listHighestNumberOfLoansByYear(year).forEach(collections::add);
            }
            return new ResponseEntity<>(collections, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
