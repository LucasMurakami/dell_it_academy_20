package com.murakami.dell_it_academy_backend.services.impl;

import com.murakami.dell_it_academy_backend.DTOs.EditionDTO;
import com.murakami.dell_it_academy_backend.entities.BetCard;
import com.murakami.dell_it_academy_backend.entities.Edition;
import com.murakami.dell_it_academy_backend.entities.LuckyClient;
import com.murakami.dell_it_academy_backend.exceptions.ResourceNotFoundException;
import com.murakami.dell_it_academy_backend.mapper.EditionMapper;
import com.murakami.dell_it_academy_backend.repositories.BetCardRepository;
import com.murakami.dell_it_academy_backend.repositories.EditionRepository;
import com.murakami.dell_it_academy_backend.repositories.LuckyClientRepository;
import com.murakami.dell_it_academy_backend.services.EditionService;
import com.murakami.dell_it_academy_backend.utils.UtilityClass;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe responsável pela lógica de negócio ao respeito da @Entity Edition.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

@Service
@AllArgsConstructor
public class EditionServiceImpl implements EditionService {

    /**
     * Atributo para interação com o banco de dados na tabela editions na camada Repository.
     */
    private EditionRepository editionRepository;

    /**
     * Atributo para interação com o banco de dados na tabela editions na camada Repository.
     */
    private LuckyClientRepository luckyClientRepository;

    /**
     * Atributo para interação com o banco de dados na tabela editions na camada Repository.
     */
    private BetCardRepository betCardRepository;

    /**
     * Atributo para interação com a classe UtilityClass.
     */
    private UtilityClass utilityClass;

    /**
     * Função para criar uma Edition no banco de dados por meio de dados da WEB na camada Repository. Atualiza a tabela editions.
     * @param editionDTO        Parâmetro para recebimento do editionDTO que contém as informações para criação da Edition no banco de dados.
     * @return                  Retorna EditionDTO da Edition salva no banco de dados para posteriormente informar a camada Controller que o objeto foi criado com sucesso ou que houve uma falha.
     */
    @Override
    @Transactional
    public EditionDTO createEdition(EditionDTO editionDTO) {
        Edition edition = EditionMapper.mapToEdition(editionDTO);
        edition.setLuckyNumbers(utilityClass.luckyNumbers());
        edition.setIsActive(true);
        Edition savedEdition = editionRepository.save(edition);
        return EditionMapper.mapToEditionDTO(savedEdition);
    }

    /**
     * Função para buscar uma Edition no banco de dados pelo ID na camada Repository.
     * @param editionId         Parâmetro para recebimento do ID da Edition a ser procurada.
     * @return                  Retorna EditionDTO da Edition encontrada no banco de dados, caso não encontre lança a exceção @ResourceNotFoundException.
     */
    @Override
    @Transactional
    public EditionDTO getEditionById(Long editionId) {
        Edition edition = editionRepository.findById(editionId).orElseThrow(
                () -> new ResourceNotFoundException("Edition not found with id: " + editionId)
        );
        return EditionMapper.mapToEditionDTO(edition);
    }

    /**
     * Função para buscar todas editions no banco de dados na camada Repository.
     * @return                  Retorna Lista de EditionDTO das editions encontradas no banco de dados, caso a Lista seja vazia lança a exceção @ResourceNotFoundExcpetion.
     */
    @Override
    @Transactional
    public List<EditionDTO> getAllEditions() {
        return editionRepository.findAll().stream().map(EditionMapper::mapToEditionDTO).toList();
    }

    /**
     * Função para buscar a última Edition no banco de dados na camada Repository.
     * @return                  Retorna EditionDTO da última Edition encontrada no banco de dados, caso não encontre lança a exceção @ResourceNotFound.
     */
    @Override
    @Transactional
    public EditionDTO getLastEdition() {
        Edition edition = editionRepository.findLast();
        return EditionMapper.mapToEditionDTO(edition);
    }

    /**
     * Função para verificar se existem vencedores da edição. Atualiza a tabela editions.
     * @return                  Retorna EditionDTO com todos Atributos atualizados por meio do sorteio.
     */
    @Override
    @Transactional
    public EditionDTO searchWinners() {
        Edition edition = EditionMapper.mapToEdition(getLastEdition());
        Set<Integer> luckyNumbers = new HashSet<>(edition.getLuckyNumbers());
        List<LuckyClient> luckyClients = new ArrayList<>();

        int luckybets = 0;
        int rounds = 0;

        while(luckybets < 1 && rounds < 26) {
            for (BetCard betCard: edition.getBetCards()) {
                if(luckyNumbers.containsAll(utilityClass.getNumbers(betCard))){
                    LuckyClient lucky = new LuckyClient(betCard.getClient(), betCard);
                    luckyClients.add(lucky);
                    betCard.setLuckyClient(lucky);
                    betCardRepository.save(betCard);
                    luckybets += 1;
                }
            }
            rounds++;
            if(!luckyClients.isEmpty()) {
                break;
            }
            if(rounds <= 25) {
                luckyNumbers = utilityClass.addLuckyNumber(luckyNumbers);
            }
        }

        if(!luckyClients.isEmpty()) {
            for (LuckyClient luckyClient: luckyClients) {
                luckyClient.setPrize(utilityClass.winnerPrize(luckybets, edition.getBetCards().size(), rounds));
            }
        }

        luckyClientRepository.saveAll(luckyClients);


        edition.setLuckyClients(new HashSet<>(luckyClients));
        edition.setLuckyNumbers(luckyNumbers);
        edition.setLuckyBets(luckybets);
        edition.setLotteryRounds(rounds);
        edition.setIsActive(false);

        Edition savedEdition = editionRepository.save(edition);
        return EditionMapper.mapToEditionDTO(savedEdition);
    }

}
