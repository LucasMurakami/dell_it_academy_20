package com.murakami.dell_it_academy_backend.services;

import com.murakami.dell_it_academy_backend.DTOs.BetCardDTO;

import java.util.List;

/**
 * Interface para camada de serviço.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

public interface BetCardService {

    /**
     * Função para criar uma BetCard no banco de dados por meio de dados da WEB na camada Repository. Atualiza a tabela bet_cards, clients e editions.
     * @param betCardDTO        Parâmetro para recebimento do betCardDTO que contém as informações para criação da BetCard no banco de dados.
     * @return                  Retorna BetCardDTO da BetCard salva no banco de dados para posteriormente informar a camada Controller que o objeto foi criado com sucesso ou que houve uma falha.
     */
    BetCardDTO createBetCard(BetCardDTO betCardDTO);

    /**
     * Função para buscar uma BetCard no banco de dados pelo ID na camada Repository.
     * @param betCardId         Parâmetro para recebimento do ID da BetCard a ser procurada.
     * @return                  Retorna BetCardDTO da BetCard encontrada no banco de dados, caso não encontre lança a exceção @ResourceNotFoundException.
     */
    BetCardDTO getBetCardById(Long betCardId);

    /**
     * Função para buscar todas BetCards no banco de dados na camada Repository.
     * @return                  Retorna Lista de BetCardDTO das BetCards encontradas no banco de dados, caso a Lista seja vazia lança a exceção @ResourceNotFoundExcpetion.
     */
    List<BetCardDTO> getAllBetCards();
}
