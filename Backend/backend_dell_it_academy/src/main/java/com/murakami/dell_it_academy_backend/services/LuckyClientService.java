package com.murakami.dell_it_academy_backend.services;


import com.murakami.dell_it_academy_backend.DTOs.LuckyClientDTO;
import com.murakami.dell_it_academy_backend.DTOs.NumberCountDTO;

import java.util.List;

/**
 * Interface para camada de serviço.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

public interface LuckyClientService {

    /**
     * Função para criar um luckyClient no banco de dados por meio de dados da WEB na camada Repository. Atualiza a tabela lucky_clients.
     * @param luckyClientDTO    Parâmetro para recebimento do luckyClientDTO que contém as informações para criação do luckyClient no banco de dados.
     * @return                  Retorna LuckyClientDTO do LuckyClient salvo no banco de dados para posteriormente informar a camada Controller que o objeto foi criado com sucesso ou que houve uma falha.
     */
    LuckyClientDTO createLuckyClient(LuckyClientDTO luckyClientDTO);

    /**
     * Função para buscar um LuckyClient no banco de dados pelo ID na camada Repository.
     * @param luckyClientId     Parâmetro para recebimento do ID do LuckyClient a ser procurado.
     * @return                  Retorna LuckyClientDTO do LuckyClient encontrado no banco de dados, caso não encontre lança a exceção @ResourceNotFoundException.
     */
    LuckyClientDTO getLuckyClientById(Long luckyClientId);

    /**
     * Função para buscar todos luckyClients no banco de dados na camada Repository.
     * @return                  Retorna Lista de LuckyClientDTO dos luckyClients encontrados no banco de dados, caso a Lista seja vazia lança a exceção @ResourceNotFoundExcpetion.
     */
    List<LuckyClientDTO> getAllLuckyClients();
}
