package com.murakami.dell_it_academy_backend.controllers;

import com.murakami.dell_it_academy_backend.DTOs.EditionDTO;
import com.murakami.dell_it_academy_backend.services.EditionService;
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
@RequestMapping("api/editions")

public class EditionController {

    /**
     * Atributo para interação na camada Service.
     */
    private final EditionService editionService;

    /**
     * Construtor para injeção do Service na camada Controller.
     * @param editionService      Parâmetro para injeção da dependência.
     */


    public EditionController(EditionService editionService) {
        this.editionService = editionService;
    }

    /**
     * Função para receber um POST Request no caminho ("/") que retorna uma Edition criado no banco de dados.
     * @param editionDTO                Parâmetro para recebimento do EditionDTO que contém as informações para criação da Edition no banco de dados.
     * @return                          Retorna ResponseEntity com a Edition criado e um HttpStatus CREATED. (Response Code 201).
     */

    @PostMapping
    public ResponseEntity<EditionDTO> createEdition(@RequestBody EditionDTO editionDTO) {
        EditionDTO edition = editionService.createEdition(editionDTO);
        return new ResponseEntity<>(edition, HttpStatus.CREATED);
    }

    /**
     * Função para receber um GET Request no caminho ("/{id}") que retorna uma Edition com o determinado ID do banco de dados.
     * @param editionId                 Parâmetro para recebimento do EditionID a ser procurado.
     * @return                          Retorna ResponseEntity com o Edition encontrado e um HttpStatus OK. (Response Code 200).
     */

    @GetMapping("/{id}")
    public ResponseEntity<EditionDTO> getEditionById(@PathVariable("id") Long editionId) {
        EditionDTO editionDTO = editionService.getEditionById(editionId);
        return ResponseEntity.ok(editionDTO);
    }

    /**
     * Função para receber um GET Request no caminho ("/") que retorna todas Edition do banco de dados.
     * @return                          Retorna ResponseEntity com todas Edition encontradas e um HttpStatus OK. (Response Code 200).
     */

    @GetMapping
    public ResponseEntity<List<EditionDTO>> getAllEditions() {
        List<EditionDTO> editions = editionService.getAllEditions();
        return ResponseEntity.ok(editions);
    }

    /**
     * Função para receber um GET Request no caminho ("/last") que retorna a última Edition do banco de dados.
     * @return                          Retorna ResponseEntity com a última Edition encontrada e um HttpStatus OK. (Response Code 200).
     */

    @GetMapping("/last")
    public ResponseEntity<EditionDTO> getLastEdition() {
        EditionDTO editionDTO = editionService.getLastEdition();
        return ResponseEntity.ok(editionDTO);
    }

    /**
     * Função para receber um GET Request no caminho ("/winners") que realiza o sorteio da edição, retornando a edição com toda informação da última Edition.
     * @return                          Retorna ResponseEntity com a edição do sorteio realizada e um HttpStatus OK. (Response Code 200).
     */

    @GetMapping("/winners")
    public ResponseEntity<EditionDTO> getWinners() {
        EditionDTO editionDTO = editionService.searchWinners();
        return ResponseEntity.ok(editionDTO);
    }
}
