package com.murakami.dell_it_academy_backend.controllers;

import com.murakami.dell_it_academy_backend.DTOs.NumberBetCardDTO;
import com.murakami.dell_it_academy_backend.DTOs.NumberCountDTO;
import com.murakami.dell_it_academy_backend.services.NumberBetCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe responsável por lidar com requisições da camada View.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

@CrossOrigin("*")
@RestController
@RequestMapping("api/numberBetcards")

public class NumberBetCardController {

    /**
     * Atributo para interação na camada Service.
     */
    private final NumberBetCardService numberBetCardService;

    /**
     * Construtor para injeção do Service na camada Controller.
     * @param numberBetCardService      Parâmetro para injeção da dependência.
     */

    public NumberBetCardController(NumberBetCardService numberBetCardService) {
        this.numberBetCardService = numberBetCardService;
    }

    /**
     * Função para receber um POST Request no caminho ("/") que retorna um NumberBetCard criado no banco de dados.
     * @param numberBetCardDTO          Parâmetro para recebimento do NumberCardDTO que contém as informações para criação do NumberCard no banco de dados.
     * @return                          Retorna ResponseEntity com o NumberCardBetCard criado e um HttpStatus CREATED. (Response Code 201).
     */

    @PostMapping
    public ResponseEntity<NumberBetCardDTO> createNumberCard(@RequestBody NumberBetCardDTO numberBetCardDTO) {
        NumberBetCardDTO numberBetCard = numberBetCardService.createNumberBetCard(numberBetCardDTO);
        return new ResponseEntity<>(numberBetCard, HttpStatus.CREATED);
    }

    /**
     * Função para receber um GET Request no caminho ("/{id}") que retorna um NumberBetCard com o determinado ID do banco de dados.
     * @param numberBetCardId           Parâmetro para recebimento do NumberBetCardID a ser procurado.
     * @return                          Retorna ResponseEntity com o NumberBetCard encontrado e um HttpStatus OK. (Response Code 200).
     */
    @GetMapping("/{id}")
    public ResponseEntity<NumberBetCardDTO> getNumberBetCardById(@PathVariable("id") Long numberBetCardId) {
        NumberBetCardDTO numberBetCardDTO = numberBetCardService.getNumberBetCardById(numberBetCardId);
        return ResponseEntity.ok(numberBetCardDTO);
    }

    /**
     * Função para receber um GET Request no caminho ("/") que retorna todos NumberBetCard do banco de dados.
     * @return                          Retorna ResponseEntity com todos NumberBetCard encontrados e um HttpStatus OK. (Response Code 200).
     */

    @GetMapping()
    public ResponseEntity<List<NumberBetCardDTO>> getAllNumberBetCards() {
        List<NumberBetCardDTO> numberBetCards = numberBetCardService.getAllNumberBetCards();
        return ResponseEntity.ok(numberBetCards);
    }

    @GetMapping("/highest/numbersBet/{id}")
    public ResponseEntity<List<NumberCountDTO>> getMostNumbersBetted(@PathVariable("id") Long editionId) {
        List<NumberCountDTO> numbersCountDTO = numberBetCardService.findHighestNumbersByEditionId(editionId);
        return ResponseEntity.ok(numbersCountDTO);
    }


}
