package com.murakami.dell_it_academy_backend.services;


import com.murakami.dell_it_academy_backend.DTOs.NumberBetCardDTO;
import com.murakami.dell_it_academy_backend.DTOs.NumberCountDTO;

import java.util.List;

/**
 * Interface para camada de serviço.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

public interface NumberBetCardService {

    /**
     * Função para criar um NumberBetCard no banco de dados por meio de dados da WEB na camada Repository. Atualiza a tabela bet_cards e bet_numbers.
     * @param numberBetCardDTO        Parâmetro para recebimento do NumberBetCardDTO que contém as informações para criação do NumberBetCard no banco de dados.
     * @return                        Retorna NumberBetCardDTO do NumberBetCard salvo no banco de dados para posteriormente informar a camada Controller que o objeto foi criado com sucesso ou que houve uma falha.
     */
    NumberBetCardDTO createNumberBetCard(NumberBetCardDTO numberBetCardDTO);

    /**
     * Função para buscar um NumberBetCard no banco de dados pelo ID na camada Repository.
     * @param numberBetCardId         Parâmetro para recebimento do ID do NumberBetCard a ser procurado.
     * @return                        Retorna NumberBetCardDTO do NumberBetCard encontrado no banco de dados, caso não encontre lança a exceção @ResourceNotFoundException.
     */
    NumberBetCardDTO getNumberBetCardById(Long numberBetCardId);

    /**
     * Função para buscar todos numbersBetCard no banco de dados na camada Repository.
     * @return                  Retorna Lista de NumberBetCardDTO dos numbersBetCard encontrados no banco de dados, caso a Lista seja vazia lança a exceção @ResourceNotFoundExcpetion.
     */
    List<NumberBetCardDTO> getAllNumberBetCards();

    List<NumberCountDTO> findHighestNumbersByEditionId(Long editionId);
}
