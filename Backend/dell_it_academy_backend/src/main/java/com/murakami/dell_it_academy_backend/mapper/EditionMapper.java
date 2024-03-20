package com.murakami.dell_it_academy_backend.mapper;


import com.murakami.dell_it_academy_backend.DTOs.EditionDTO;
import com.murakami.dell_it_academy_backend.entities.Edition;

import java.util.HashSet;

/**
 * Classe responsável para transicionar entre DTO e Entity e vice-versa da @Entity Edition para facilitar o manejamemento de dados vindos da da web ao banco de dados e vice-versa.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */


public class EditionMapper {

    /**
     * Função para conversão de @Entity para DTO.
     *
     * @param edition           Parâmetro para converter @Entity Edition em EditionDTO.
     * @return                  Retorna EdidtionDTO.
     */
    public static EditionDTO mapToEditionDTO(Edition edition) {
        EditionDTO editionDTO = new EditionDTO();

        if(edition != null){
            editionDTO.setId(edition.getId());

            if(edition.getBetCards() != null)
                editionDTO.setBetCards(new HashSet<>(edition.getBetCards()));

            editionDTO.setIsActive(edition.getIsActive());
            editionDTO.setLuckyBets(edition.getLuckyBets());

            if(edition.getLuckyClients() != null)
                editionDTO.setLuckyClients(new HashSet<>(edition.getLuckyClients()));

            editionDTO.setLotteryRounds(edition.getLotteryRounds());
            editionDTO.setLuckyNumbers(edition.getLuckyNumbers());
        }

        return editionDTO;
    }

    /**
     * Função para conversão de DTO para @Entity.
     *
     * @param editionDTO        Parâmetro para converter EditionDTO em @Entity Edition.
     * @return                  Retorna Edition.
     */

    public static Edition mapToEdition(EditionDTO editionDTO) {
        Edition edition = new Edition();
        edition.setId(editionDTO.getId());

        if(editionDTO.getBetCards() != null)
            edition.setBetCards(new HashSet<>(editionDTO.getBetCards()));

        edition.setIsActive(editionDTO.getIsActive());
        edition.setLuckyBets(editionDTO.getLuckyBets());

        if(editionDTO.getLuckyClients() != null)
            edition.setLuckyClients(new HashSet<>(edition.getLuckyClients()));

        edition.setLotteryRounds(edition.getLotteryRounds());
        edition.setLuckyNumbers(editionDTO.getLuckyNumbers());

        return edition;
    }
}
