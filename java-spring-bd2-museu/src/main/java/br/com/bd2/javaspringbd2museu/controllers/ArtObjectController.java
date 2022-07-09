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
  public ResponseEntity<List<ArtObject>> getAllArtObjects(@RequestParam(required = false) String title) {
    try {
      List<ArtObject> artObjects = new ArrayList<ArtObject>();
      this.artObjectRepository.findAll().forEach(artObjects::add);
      return new ResponseEntity<>(artObjects, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
