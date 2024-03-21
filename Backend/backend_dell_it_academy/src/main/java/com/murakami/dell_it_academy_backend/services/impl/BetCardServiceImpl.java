package com.murakami.dell_it_academy_backend.services.impl;

import com.murakami.dell_it_academy_backend.DTOs.BetCardDTO;
import com.murakami.dell_it_academy_backend.entities.BetCard;
import com.murakami.dell_it_academy_backend.entities.Client;
import com.murakami.dell_it_academy_backend.entities.Edition;
import com.murakami.dell_it_academy_backend.exceptions.ResourceNotFoundException;
import com.murakami.dell_it_academy_backend.mapper.BetCardMapper;
import com.murakami.dell_it_academy_backend.repositories.BetCardRepository;
import com.murakami.dell_it_academy_backend.repositories.ClientRepository;
import com.murakami.dell_it_academy_backend.repositories.EditionRepository;
import com.murakami.dell_it_academy_backend.services.BetCardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe responsável pela lógica de negócio ao respeito da @Entity BetCard.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

@Service
@AllArgsConstructor
public class BetCardServiceImpl implements BetCardService {

    /**
     * Atributo para interação com o banco de dados da tabela bet_cards na camada Repository.
     */
    private BetCardRepository betCardRepository;

    /**
     * Atributo para interação com o banco de dados na tabela clients na camada Repository.
     */
    private ClientRepository clientRepository;

    /**
     * Atributo para interação com o banco de dados na tabela editions na camada Repository.
     */
    private EditionRepository editionRepository;

    /**
     * Função para criar uma BetCard no banco de dados por meio de dados da WEB na camada Repository. Atualiza a tabela bet_cards, clients e editions.
     * @param betCardDTO        Parâmetro para recebimento do betCardDTO que contém as informações para criação da BetCard no banco de dados.
     * @return                  Retorna BetCardDTO da BetCard salva no banco de dados para posteriormente informar a camada Controller que o objeto foi criado com sucesso ou que houve uma falha.
     */
    @Override
    @Transactional
    public BetCardDTO createBetCard(BetCardDTO betCardDTO) {

        Long clientId = betCardDTO.getClientId();
        Long editionId = betCardDTO.getEditionId();

        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundException("Cliente não encontrado com id: " + clientId)
        );

        Edition edition = editionRepository.findById(editionId).orElseThrow(
                () -> new ResourceNotFoundException("Edição não encontrada com id: " + editionId)
        );

        BetCard betCard = BetCardMapper.mapToBetCard(betCardDTO);
        betCard.setClient(client);
        betCard.setEdition(edition);

        BetCard savedBetCard = betCardRepository.save(betCard);

        // Atualizar tabela de Editions.
        Set<BetCard> editionBetCards = edition.getBetCards();
        editionBetCards.add(betCard);
        edition.setBetCards(editionBetCards);
        editionRepository.save(edition);

        // Atualizar tabela de Clients.
        Set<BetCard> clientBetCards =  new HashSet<>(client.getBetCards());
        clientBetCards.add(betCard);
        client.setBetCards(new HashSet<>(clientBetCards));
        clientRepository.save(client);

        return BetCardMapper.mapToBetCardDTO(savedBetCard);
    }

    /**
     * Função para buscar uma BetCard no banco de dados pelo ID na camada Repository.
     * @param betCardId         Parâmetro para recebimento do ID da BetCard a ser procurada.
     * @return                  Retorna BetCardDTO da BetCard encontrada no banco de dados, caso não encontre lança a exceção @ResourceNotFoundException.
     */
    @Override
    @Transactional
    public BetCardDTO getBetCardById(Long betCardId) {
        BetCard betCard = betCardRepository.findById(betCardId).orElseThrow(
                () -> new ResourceNotFoundException("BetCard não encontrada com id: " + betCardId)
        );
        return BetCardMapper.mapToBetCardDTO(betCard);
    }

    /**
     * Função para buscar todas BetCards no banco de dados na camada Repository.
     * @return                  Retorna Lista de BetCardDTO das BetCards encontradas no banco de dados, caso a Lista seja vazia lança a exceção @ResourceNotFoundExcpetion.
     */

    @Override
    @Transactional
    public List<BetCardDTO> getAllBetCards() {
        List<BetCardDTO> betCards = betCardRepository.findAll().stream().map(BetCardMapper::mapToBetCardDTO).toList();
        if(betCards.isEmpty()) throw new ResourceNotFoundException("Nenhuma BetCard encontrada.");
        return betCards;
    }

}
