package com.murakami.dell_it_academy_backend.services.impl;

import com.murakami.dell_it_academy_backend.DTOs.BetCardDTO;
import com.murakami.dell_it_academy_backend.DTOs.ClientDTO;
import com.murakami.dell_it_academy_backend.DTOs.ClientPutBetCardDTO;
import com.murakami.dell_it_academy_backend.entities.BetCard;
import com.murakami.dell_it_academy_backend.entities.Client;
import com.murakami.dell_it_academy_backend.exceptions.ResourceNotFoundException;
import com.murakami.dell_it_academy_backend.mapper.BetCardMapper;
import com.murakami.dell_it_academy_backend.mapper.ClientMapper;
import com.murakami.dell_it_academy_backend.repositories.BetCardRepository;
import com.murakami.dell_it_academy_backend.repositories.ClientRepository;
import com.murakami.dell_it_academy_backend.services.BetCardService;
import com.murakami.dell_it_academy_backend.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Classe responsável pela lógica de negócio ao respeito da @Entity Client.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    /**
     * Atributo para interação com o banco de dados na tabela clients na camada Repository.
     */
    private ClientRepository clientRepository;

    /**
     * Atributo para interação com o banco de dados na tabela clients na camada Repository.
     */
    private BetCardRepository betCardRepository;

    private BetCardService betCardService;

    /**
     * Função para criar um Client no banco de dados por meio de dados da WEB na camada Repository. Atualiza a tabela clients.
     * @param clientDTO         Parâmetro para recebimento do ClientDTO que contém as informações para criação do Client no banco de dados.
     * @return                  Retorna ClientDTO do Client salvo no banco de dados para posteriormente informar a camada Controller que o objeto foi criado com sucesso ou houve uma falha.
     */
    @Override
    @Transactional
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = ClientMapper.mapToClient(clientDTO);
        Client savedClient = clientRepository.save(client);
        return ClientMapper.mapToClientDTO(savedClient);
    }

    /**
     * Função para buscar um Client no banco de dados pelo ID na camada Repository.
     * @param clientId          Parâmetro para recebimento do ID do Client a ser procurado.
     * @return                  Retorna ClientDTO do Client encontrado no banco de dados, caso não encontre lança a exceção @ResourceNotFoundException.
     */
    @Override
    @Transactional(readOnly = true)
    public ClientDTO getClientById(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundException("Client not found with the id: " + clientId)
        );

        return ClientMapper.mapToClientDTO(client);
    }

    /**
     * Função para buscar todos Clients no banco de dados na camada Repository.
     * @return                  Retorna Lista de ClientDTO dos Clients encontrados no banco de dados, caso a Lista seja vazia lança a exceção @ResourceNotFoundExcpetion.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ClientDTO> getAllClients() {
        List<ClientDTO> clientList = clientRepository.findAll().stream().map(ClientMapper::mapToClientDTO).toList();
        if(clientList.isEmpty()) throw new ResourceNotFoundException("Nenhum cliente encontrado.");
        return clientList;
    }


    /**
     * Função para atualizar um cliente com determinado ID no banco de dados na camada Repository.
     * @param clientId          Parâmetro para recebimento do ID do Client a ser procurado.
     * @param updatedClientPutBetCardDTO  Parâmetro para recebimento do ClientDTO atualizado.
     * @return                  Retorna CLientDTO do Client atualizado.
     */

    @Transactional
    public ClientPutBetCardDTO updateBetCardsClient(Long clientId, ClientPutBetCardDTO updatedClientPutBetCardDTO) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado com o id: " + clientId)
        );

        betCardService.createBetCard((updatedClientPutBetCardDTO.getBetCardDTO()));

        return ClientMapper.mapToClientPutBetCardDTO(client);
    }

    /**
     * Função para buscar um Client no banco de dados pelo CPF na camada Repository.
     * @param cpf               Parâmetro para recebimento do CPF do Client a ser procurado.
     * @return                  Retorna ClientDTO do Client encontrado no banco de dados, caso não encontre lança a exceção @ResourceNotFoundException.
     */
    @Override
    @Transactional
    public List<ClientDTO> getClientByCPF(String cpf) {
        List<Client> clients = clientRepository.findClientByCpfEquals(cpf);
        List<ClientDTO> clientDTOS = new ArrayList<>();
        for (Client client: clients) {
            clientDTOS.add(ClientMapper.mapToClientDTO(client));
        }
        return clientDTOS;
    }


}
