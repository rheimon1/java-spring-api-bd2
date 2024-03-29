package br.com.bd2.javaspringbd2museu.controllers;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /*
     * Liste os objetos de arte por tipo e por classe
     */
    @GetMapping("/art-object")
    public ResponseEntity<List<ArtObject>> getAllArtObjects(
        @RequestParam(required = false) String type,
        @RequestParam(required = false) String _class
    ) {
        try {
            List<ArtObject> artObjects = new ArrayList<ArtObject>();
            if (type == null  && _class == null) {
                this.artObjectRepository.findAll().forEach(artObjects::add);
            } else if (type != null  && _class != null) {
                this.artObjectRepository.findByTypeAndClass(type, _class).forEach(artObjects::add);
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

    /*
     * Faça uma curva desses gastos
     */
    @GetMapping("/art-object/costs")
    public ResponseEntity<List<ArtObject>> getArtObjectCosts() {
        try {
            List<ArtObject> artObjects = new ArrayList<ArtObject>();
            this.artObjectRepository.findTitleAndCostOrderedByCost().forEach(artObjects::add);
            return new ResponseEntity<>(artObjects, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Faça um controle (listagem) da compra de objetos de arte por mês e por ano do museu.
     */
    @GetMapping("/art-object/permanent-collection")
    public ResponseEntity<List<ArtObject>> getAll(
        @RequestParam(required = false) String month,
        @RequestParam(required = false) String year
    ) {
        try {
            List<ArtObject> artObjects = new ArrayList<ArtObject>();
            if (month == null  && year == null) {
                this.artObjectRepository.findAllPermanentCollection().forEach(artObjects::add);
            } else if (month != null  && year != null) {
                this.artObjectRepository.findPermanentCollectionByMonthAndYear(month, year).forEach(artObjects::add);
            } else if (month != null) {
                this.artObjectRepository.findByMonth(month).forEach(artObjects::add);
            } else if (year != null) {
                this.artObjectRepository.findByYear(year).forEach(artObjects::add);
            }
            return new ResponseEntity<>(artObjects, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Faça uma controle (listagem) da quantidade de objetos emprestados por coleção, por mês e por ano.
     */
    @GetMapping("/art-object/borrowed/count")
    public ResponseEntity<List<ArtObject>> getCountOfBorrowedArtObjects(
        @RequestParam(required = false) String collectionName,
        @RequestParam(required = false) String month,
        @RequestParam(required = false) String year
    ) {
        List<ArtObject> artObjects = new ArrayList<ArtObject>();
        if (collectionName == null && month == null && year == null) {
            this.artObjectRepository.getBorrowedCount().forEach(artObjects::add);
        } else if (collectionName != null && month != null && year != null) {
            this.artObjectRepository.getBorrowedCountByCollectionNameAndMonthAndYear(collectionName, month, year).forEach(artObjects::add);
        } else if (collectionName != null && month != null && year == null) {
            this.artObjectRepository.getBorrowedCountByCollectionNameAndMonth(collectionName, month).forEach(artObjects::add);
        } else if (collectionName != null && month == null && year != null) {
            this.artObjectRepository.getBorrowedCountByCollectionNameAndYear(collectionName, year).forEach(artObjects::add);
        } else if (collectionName == null && month != null && year != null) {
            this.artObjectRepository.getBorrowedCountByMonthAndYear(month, year).forEach(artObjects::add);
        } else if (collectionName != null && month == null && year == null) {
            this.artObjectRepository.getBorrowedCountByCollectionName(collectionName).forEach(artObjects::add);
        } else if (collectionName == null && month != null && year == null) {
            this.artObjectRepository.getBorrowedCountByMonth(month).forEach(artObjects::add);
        } else if (collectionName == null && month == null && year != null) {
            this.artObjectRepository.getBorrowedCountByYear(year).forEach(artObjects::add);
        }
        return new ResponseEntity<>(artObjects, HttpStatus.OK);
    }

    /**
     * cadastre os diferentes objetos de arte comprados ou emprestados das coleções conveniadas
     * @param artObject
     * @return
     */
    @PostMapping("/art-object")
    public ResponseEntity<String> createTutorial(@RequestBody ArtObject artObject) {
        try {
            this.artObjectRepository.save(
                new ArtObject(
                    artObject.getNomeArtista(),
                    artObject.getPeriodo(),
                    artObject.getAno(),
                    artObject.getTitulo(),
                    artObject.getDescricao(),
                    artObject.getCultura(),
                    artObject.getEstilo(),
                    artObject.getCusto(),
                    artObject.getTipo()
                )
            );
            return new ResponseEntity<>("Objeto de Arte criado com sucesso.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
