package com.murakami.dell_it_academy_backend.controllers;

import com.murakami.dell_it_academy_backend.DTOs.LuckyClientDTO;
import com.murakami.dell_it_academy_backend.services.LuckyClientService;
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
@RequestMapping("api/luckyclients")

public class LuckyClientController {

    /**
     * Atributo para interação na camada Service.
     */
    private final LuckyClientService luckyClientService;

    /**
     * Construtor para injeção do Service na camada Controller.
     * @param luckyClientService        Parâmetro para injeção da dependência.
     */

    public LuckyClientController(LuckyClientService luckyClientService) {
        this.luckyClientService = luckyClientService;
    }

    /**
     * Função para receber um POST Request no caminho ("/") que retorna um LuckyClient criado no banco de dados.
     * @param luckyClientDTO            Parâmetro para recebimento do luckyClientDTO que contém as informações para criação do LuckyClient no banco de dados.
     * @return                          Retorna ResponseEntity com o LuckyClient criado e um HttpStatus CREATED. (Response Code 201).
     */

    @PostMapping
    public ResponseEntity<LuckyClientDTO> createLuckyClient(@RequestBody LuckyClientDTO luckyClientDTO) {
        LuckyClientDTO luckyClient = luckyClientService.createLuckyClient(luckyClientDTO);
        return new ResponseEntity<>(luckyClient, HttpStatus.CREATED);
    }

    /**
     * Função para receber um GET Request no caminho ("/{id}") que retorna um LuckyClient com o determinado ID do banco de dados.
     * @param luckyClientId             Parâmetro para recebimento do LuckyClientID a ser procurado.
     * @return                          Retorna ResponseEntity com o LuckyClient encontrado e um HttpStatus OK. (Response Code 200).
     */

    @GetMapping("/{id}")
    public ResponseEntity<LuckyClientDTO> getLuckyClientById(@PathVariable("id") Long luckyClientId) {
        LuckyClientDTO luckyClientDTO = luckyClientService.getLuckyClientById(luckyClientId);
        return ResponseEntity.ok(luckyClientDTO);
    }

    /**
     * Função para receber um GET Request no caminho ("/") que retorna todos LuckyClient do banco de dados.
     * @return                          Retorna ResponseEntity com todos LuckyClient encontrados e um HttpStatus OK. (Response Code 200).
     */

    @GetMapping
    public ResponseEntity<List<LuckyClientDTO>> getAllLuckyClients() {
        List<LuckyClientDTO> luckyClients = luckyClientService.getAllLuckyClients();
        return ResponseEntity.ok(luckyClients);
    }
}
