package com.murakami.dell_it_academy_backend.controllers;

import com.murakami.dell_it_academy_backend.DTOs.ClientDTO;
import com.murakami.dell_it_academy_backend.DTOs.ClientPutBetCardDTO;
import com.murakami.dell_it_academy_backend.services.ClientService;
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
@RequestMapping("api/clients")

public class ClientController {

    /**
     * Atributo para interação na camada Service.
     */
    private final ClientService clientService;

    /**
     * Construtor para injeção do Service na camada Controller.
     * @param clientService      Parâmetro para injeção da dependência.
     */

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Função para receber um POST Request no caminho ("/") que retorna um Client criado no banco de dados.
     * @param clientDTO         Parâmetro para recebimento do ClientDTO que contém as informações para criação do Client no banco de dados.
     * @return                  Retorna ResponseEntity com o Client criado e um HttpStatus CREATED. (Response Code 201).
     */

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO client = clientService.createClient(clientDTO);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    /**
     * Função para receber um GET Request no caminho ("/id/{id}") que retorna um Client com o determinado ID do banco de dados.
     * @param clientId           Parâmetro para recebimento do ClientID a ser procurado.
     * @return                   Retorna ResponseEntity com o Client encontrado e um HttpStatus OK. (Response Code 200).
     */

    @GetMapping("/id/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Long clientId) {
        ClientDTO clientDTO = clientService.getClientById(clientId);
        return ResponseEntity.ok(clientDTO);
    }

    /**
     * Função para receber um GET Request no caminho ("/") que retorna todos Client do banco de dados.
     * @return                          Retorna ResponseEntity com todos Client encontrados e um HttpStatus OK. (Response Code 200).
     */

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        List<ClientDTO> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    /**
     * Função para receber um GET Request no caminho ("/cpf/{cpf}") que retorna um Client com o determinado cpf do banco de dados.
     * @param clientCPF          Parâmetro para recebimento do cpf a ser procurado.
     * @return                   Retorna ResponseEntity com o Client encontrado e um HttpStatus OK. (Response Code 200).
     */

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<List<ClientDTO>> getClientByCPF(@PathVariable("cpf") String clientCPF) {
        List<ClientDTO> clientDTO = clientService.getClientByCPF(clientCPF);
        return ResponseEntity.ok(clientDTO);
    }

    /**
     * Função para receber um PUT Request no caminho ("/{id}") que retorna um Client atualizado do banco de dados.
     * @param clientId          Parâmetro para recebimento do ClientID a ser procurado.
     * @param updatedClient     Parâmetro para recebimento do Client atualizado.
     * @return                  Retorna ResponseEntity com o Client atualizado e um HttpStatus OK. (Response Code 200).
     */

    @PutMapping("/id/{id}")
    public ResponseEntity<ClientPutBetCardDTO> updateBetCardsClient(@PathVariable("id") Long clientId, @RequestBody ClientPutBetCardDTO updatedClient) {
        ClientPutBetCardDTO clientPutBetCardDTO = clientService.updateBetCardsClient(clientId, updatedClient);
        return ResponseEntity.ok(clientPutBetCardDTO);
    }
}
