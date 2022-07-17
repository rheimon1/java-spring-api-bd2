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

import br.com.bd2.javaspringbd2museu.repository.ArtObjectRepository;
import br.com.bd2.javaspringbd2museu.model.ArtObject;

@RestController
@RequestMapping("/api")
public class ArtObjectController {
    @Autowired
    ArtObjectRepository artObjectRepository;
    
    @GetMapping("/art-object")
    public ResponseEntity<List<ArtObject>> getAllArtObjects(
        @RequestParam(required = false) String type, 
        @RequestParam(required = false) String _class
    ) {
        try {
            List<ArtObject> artObjects = new ArrayList<ArtObject>();
            if (type == null  && _class == null) {
                this.artObjectRepository.findAll().forEach(artObjects::add);
            } else if (type != null) {
                this.artObjectRepository.findByType(type).forEach(artObjects::add);
            } else if (_class != null) {
                this.artObjectRepository.findByClass(_class).forEach(artObjects::add);
            }
            return new ResponseEntity<>(artObjects, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/art-object/borrowed/count")
    public ResponseEntity<Integer> getCountOfBorrowedArtObjects(
        @RequestParam(required = false) String collectionName, 
        @RequestParam(required = false) String month,
        @RequestParam(required = false) String year
    ) {
        int count = 0;
        if (collectionName == null && month == null && year == null) {
            count = this.artObjectRepository.getBorrowedCount();
        } else if (collectionName != null) {
            count = this.artObjectRepository.getBorrowedCountByCollectionName(collectionName);
        } else if (month != null) {
            count = this.artObjectRepository.getBorrowedCountByMonth(month);
        } else if (year != null) {
            count = this.artObjectRepository.getBorrowedCountByYear(year);
        }
        return new ResponseEntity<>(Integer.valueOf(count), HttpStatus.OK);
    }
}
