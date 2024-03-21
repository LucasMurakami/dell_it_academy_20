package com.murakami.dell_it_academy_backend.services;


import com.murakami.dell_it_academy_backend.DTOs.ClientDTO;
import com.murakami.dell_it_academy_backend.DTOs.ClientPutBetCardDTO;

import java.util.List;

/**
 * Interface para camada de serviço.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

public interface ClientService {

    /**
     * Função para criar um Client no banco de dados por meio de dados da WEB na camada Repository. Atualiza a tabela clients.
     * @param clientDTO         Parâmetro para recebimento do ClientDTO que contém as informações para criação do Client no banco de dados.
     * @return                  Retorna ClientDTO do Client salvo no banco de dados para posteriormente informar a camada Controller que o objeto foi criado com sucesso ou houve uma falha.
     */
    ClientDTO createClient(ClientDTO clientDTO);

    /**
     * Função para buscar um Client no banco de dados pelo ID na camada Repository.
     * @param clientId          Parâmetro para recebimento do ID do Client a ser procurado.
     * @return                  Retorna ClientDTO do Client encontrado no banco de dados, caso não encontre lança a exceção @ResourceNotFoundException.
     */
    ClientDTO getClientById(Long clientId);

    /**
     * Função para buscar todos Clients no banco de dados na camada Repository.
     * @return                  Retorna Lista de ClientDTO dos Clients encontrados no banco de dados, caso a Lista seja vazia lança a exceção @ResourceNotFoundExcpetion.
     */
    List<ClientDTO> getAllClients();

    /**
     * Função que atualiza as BetCards do Client em espeçifico.
     * @param clientId          Parâmetro para recebimento do ID do Client a ser procurado.
     * @param updatedClientDTO  Parâmetro para recebimento do ClientDTO com as BetCards atualizadas.
     * @return                  Retorna ClientDTO do Client atualizado.
     */
    ClientPutBetCardDTO updateBetCardsClient(Long clientId, ClientPutBetCardDTO updatedClientDTO);

    /**
     * Função para buscar um Client no banco de dados pelo CPF na camada Repository.
     * @param cpf               Parâmetro para recebimento do CPF para a busca de Clients a serem procurados.
     * @return                  Retorna uma Lista de ClientsDTO.
     */
    List<ClientDTO> getClientByCPF(String cpf);
}
