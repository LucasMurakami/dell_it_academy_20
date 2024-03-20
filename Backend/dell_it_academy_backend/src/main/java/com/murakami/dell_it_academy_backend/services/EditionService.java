package com.murakami.dell_it_academy_backend.services;


import com.murakami.dell_it_academy_backend.DTOs.EditionDTO;

import java.util.List;

/**
 * Interface para camada de serviço.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

public interface EditionService {

    /**
     * Função para criar uma Edition no banco de dados por meio de dados da WEB na camada Repository. Atualiza a tabela editions.
     * @param editionDTO        Parâmetro para recebimento do editionDTO que contém as informações para criação da Edition no banco de dados.
     * @return                  Retorna EditionDTO da Edition salva no banco de dados para posteriormente informar a camada Controller que o objeto foi criado com sucesso ou que houve uma falha.
     */
    EditionDTO createEdition(EditionDTO editionDTO);

    /**
     * Função para buscar uma Edition no banco de dados pelo ID na camada Repository.
     * @param editionId         Parâmetro para recebimento do ID da Edition a ser procurada.
     * @return                  Retorna EditionDTO da Edition encontrada no banco de dados, caso não encontre lança a exceção @ResourceNotFoundException.
     */
    EditionDTO getEditionById(Long editionId);

    /**
     * Função para buscar todas editions no banco de dados na camada Repository.
     * @return                  Retorna Lista EditionDTO das editions encontradas no banco de dados, caso a Lista seja vazia lança a exceção @ResourceNotFoundExcpetion.
     */
    List<EditionDTO> getAllEditions();

    /**
     * Função para buscar a última Edition no banco de dados na camada Repository.
     * @return                  Retorna EditionDTO da última Edition encontrada no banco de dados, caso não encontre lança a exceção @ResourceNotFound.
     */
    EditionDTO getLastEdition();

    /**
     * Função para verificar se existem vencedores da edição. Atualiza a tabela editions.
     * @return                  Retorna EditionDTO com todos Atributos atualizados por meio do sorteio.
     */
    EditionDTO searchWinners();
}
