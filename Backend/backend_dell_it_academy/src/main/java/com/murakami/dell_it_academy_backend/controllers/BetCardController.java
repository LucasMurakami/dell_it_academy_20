package com.murakami.dell_it_academy_backend.controllers;

import com.murakami.dell_it_academy_backend.DTOs.BetCardDTO;
import com.murakami.dell_it_academy_backend.services.BetCardService;
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
@RequestMapping("api/betcards")

public class BetCardController {

    /**
     * Atributo para interação na camada Service.
     */

    private final BetCardService betCardService;

    /**
     * Construtor para injeção do Service na camada Controller.
     * @param betCardService      Parâmetro para injeção da dependência.
     */

    public BetCardController(BetCardService betCardService) {
        this.betCardService = betCardService;
    }

    /**
     * Função para receber um POST Request no caminho ("/") que retorna uma BetCard criada no banco de dados.
     * @param betCardDTO         Parâmetro para recebimento da BetCardDTO que contém as informações para criação da BetCard no banco de dados.
     * @return                   Retorna ResponseEntity com a BetCard criada e um HttpStatus CREATED. (Response Code 201).
     */

    @PostMapping
    public ResponseEntity<BetCardDTO> createBetCard(@RequestBody BetCardDTO betCardDTO) {
        BetCardDTO betCard = betCardService.createBetCard(betCardDTO);
        return new ResponseEntity<>(betCard, HttpStatus.CREATED);
    }

    /**
     * Função para receber um GET Request no caminho ("/{id}") que retorna uma BetCard com o determinado ID do banco de dados.
     * @param betCardId           Parâmetro para recebimento da BetCardId a ser procurado.
     * @return                    Retorna ResponseEntity com a BetCard encontrada e um HttpStatus OK. (Response Code 200).
     */

    @GetMapping("/{id}")
    public ResponseEntity<BetCardDTO> getBetCardById(@PathVariable("id") Long betCardId) {
        BetCardDTO betCardDTO = betCardService.getBetCardById(betCardId);
        return ResponseEntity.ok(betCardDTO);
    }

    /**
     * Função para receber um GET Request no caminho ("/") que retorna todas BetCard do banco de dados.
     * @return                    Retorna ResponseEntity com todas BetCard encontradas e um HttpStatus OK. (Response Code 200).
     */

    @GetMapping
    public ResponseEntity<List<BetCardDTO>> getAllBetCards() {
        List<BetCardDTO> betCards = betCardService.getAllBetCards();
        return ResponseEntity.ok(betCards);
    }
}
