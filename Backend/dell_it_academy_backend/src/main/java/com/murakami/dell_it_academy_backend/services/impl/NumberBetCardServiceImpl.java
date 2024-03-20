package com.murakami.dell_it_academy_backend.services.impl;

import com.murakami.dell_it_academy_backend.DTOs.NumberBetCardDTO;
import com.murakami.dell_it_academy_backend.DTOs.NumberCountDTO;
import com.murakami.dell_it_academy_backend.entities.BetCard;
import com.murakami.dell_it_academy_backend.entities.NumberBetCard;
import com.murakami.dell_it_academy_backend.exceptions.ResourceNotFoundException;
import com.murakami.dell_it_academy_backend.mapper.NumberBetCardMapper;
import com.murakami.dell_it_academy_backend.repositories.BetCardRepository;
import com.murakami.dell_it_academy_backend.repositories.NumberBetCardRepository;
import com.murakami.dell_it_academy_backend.services.NumberBetCardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Classe responsável pela lógica de negócio ao respeito da @Entity NumberBetCard.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

@Service
@AllArgsConstructor
public class NumberBetCardServiceImpl implements NumberBetCardService {

    /**
     * Atributo para interação com o banco de dados na tabela bet_numbers na camada Repository.
     */
    private NumberBetCardRepository numberBetCardRepository;

    /**
     * Atributo para interação com o banco de dados na tabela bet_cards na camada Repository.
     */
    private BetCardRepository betCardRepository;

    /**
     * Função para criar um NumberBetCard no banco de dados por meio de dados da WEB na camada Repository. Atualiza a tabela bet_cards e bet_numbers.
     * @param numberBetCardDTO        Parâmetro para recebimento do NumberBetCardDTO que contém as informações para criação do NumberBetCard no banco de dados.
     * @return                        Retorna NumberBetCardDTO do NumberBetCard salvo no banco de dados para posteriormente informar a camada Controller que o objeto foi criado com sucesso ou que houve uma falha.
     */
    @Override
    @Transactional
    public NumberBetCardDTO createNumberBetCard(NumberBetCardDTO numberBetCardDTO) {
        Long betCardId = numberBetCardDTO.getBetCardId();
        BetCard betcard = betCardRepository.findById(betCardId).orElseThrow(
                () -> new ResourceNotFoundException("Nenhuma betcard encontrada.")
        );

        NumberBetCard numberBetCard = NumberBetCardMapper.mapToNumberBetCard(numberBetCardDTO);
        numberBetCard.setBetCard(betcard);

        NumberBetCard savedNumberBetCard = numberBetCardRepository.save(numberBetCard);

        // Atualizar tabela betCards
        Set<NumberBetCard> numbersBetCard = betcard.getNumbers();
        numbersBetCard.add(numberBetCard);
        betcard.setNumbers(numbersBetCard);
        betCardRepository.save(betcard);

        return NumberBetCardMapper.mapToNumberBetCardDTO(savedNumberBetCard);
    }

    /**
     * Função para buscar um NumberBetCard no banco de dados pelo ID na camada Repository.
     * @param numberBetCardId         Parâmetro para recebimento do ID do NumberBetCard a ser procurado.
     * @return                        Retorna NumberBetCardDTO do NumberBetCard encontrado no banco de dados, caso não encontre lança a exceção @ResourceNotFoundException.
     */
    @Override
    @Transactional
    public NumberBetCardDTO getNumberBetCardById(Long numberBetCardId) {
        NumberBetCard numberBetCard = numberBetCardRepository.findById(numberBetCardId).orElseThrow(
                () -> new ResourceNotFoundException("Number Bet Card not found with the id: " + numberBetCardId)
        );

        return NumberBetCardMapper.mapToNumberBetCardDTO(numberBetCard);
    }

    /**
     * Função para buscar todos numbersBetCard no banco de dados na camada Repository.
     * @return                  Retorna Lista de NumberBetCardDTO dos numbersBetCard encontrados no banco de dados, caso a Lista seja vazia lança a exceção @ResourceNotFoundExcpetion.
     */
    @Override
    @Transactional
    public List<NumberBetCardDTO> getAllNumberBetCards() {
        List<NumberBetCardDTO> numbersBetCard = numberBetCardRepository.findAll().stream().map(NumberBetCardMapper::mapToNumberBetCardDTO).toList();
        if(numbersBetCard.isEmpty()) throw new ResourceNotFoundException("No Number Bet Card found.");
        return numbersBetCard;
    }

    @Transactional
    public List<NumberCountDTO> findHighestNumbersByEditionId(Long editionId) {
        List<Object[]> results = numberBetCardRepository.findHighestNumbersByEditionId(editionId);
        List<NumberCountDTO> numberCountDTOS = new ArrayList<>();
        for (Object[] result : results) {
            Long id = ((Number) result[0]).longValue();
            Integer number = (Integer) result[0];
            Long count = ((Number) result[1]).longValue();
            numberCountDTOS.add(new NumberCountDTO(id, editionId, number, count));
        }
        return numberCountDTOS;
    }
}
