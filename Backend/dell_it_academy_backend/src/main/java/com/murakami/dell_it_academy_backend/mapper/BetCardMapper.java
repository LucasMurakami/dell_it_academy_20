package com.murakami.dell_it_academy_backend.mapper;


import com.murakami.dell_it_academy_backend.DTOs.BetCardDTO;
import com.murakami.dell_it_academy_backend.entities.BetCard;

import java.util.HashSet;

/**
 * Classe responsável para transicionar entre DTO e Entity e vice-versa da @Entity BetCard para facilitar o manejamemento de dados vindos da da web ao banco de dados e vice-versa.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

public class BetCardMapper {

    /**
     * Função para conversão de @Entity para DTO.
     *
     * @param betCard           Parâmetro para converter @Entity BetCard em BetCardDTO.
     * @return                  Retorna BetCardDTO.
     */
    public static BetCardDTO mapToBetCardDTO(BetCard betCard) {
        BetCardDTO betCardDTO = new BetCardDTO();

        betCardDTO.setId(betCard.getId());

        if(betCard.getNumbers() != null)
            betCardDTO.setNumbers(new HashSet<>(betCard.getNumbers()));
        else
            betCardDTO.setNumbers(new HashSet<>());

        betCardDTO.setClientId(betCard.getClient().getId());
        betCardDTO.setEditionId(betCard.getEdition().getId());

        return betCardDTO;
    }

    /**
     * Função para conversão de DTO para @Entity.
     *
     * @param betCardDTO        Parâmetro para converter BetCardDTO em @Entity Betcard.
     * @return                  Retorna BetCard.
     */

    public static BetCard mapToBetCard(BetCardDTO betCardDTO) {
        BetCard betCard = new BetCard();

        betCard.setId(betCardDTO.getId());

        if(betCardDTO.getNumbers() != null)
            betCard.setNumbers(new HashSet<>(betCardDTO.getNumbers()));
        else
            betCard.setNumbers(new HashSet<>());

        return betCard;
    }


}
