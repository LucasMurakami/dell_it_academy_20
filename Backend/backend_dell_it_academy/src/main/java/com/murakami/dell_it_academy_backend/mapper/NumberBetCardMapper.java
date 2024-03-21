package com.murakami.dell_it_academy_backend.mapper;


import com.murakami.dell_it_academy_backend.DTOs.NumberBetCardDTO;
import com.murakami.dell_it_academy_backend.entities.NumberBetCard;

/**
 * Classe responsável para transicionar entre DTO e Entity e vice-versa da @Entity NumberBetCard para facilitar o manejamemento de dados vindos da da web ao banco de dados e vice-versa.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

public class NumberBetCardMapper {

    /**
     * Função para conversão de @Entity para DTO.
     *
     * @param numberBetCard         Parâmetro para converter @Entity NumberBetCard em NumberBetCardDTO.
     * @return                      Retorna NumberBetCardDTO.
     */

    public static NumberBetCardDTO mapToNumberBetCardDTO(NumberBetCard numberBetCard) {
        NumberBetCardDTO numberBetCardDTO = new NumberBetCardDTO();
        numberBetCardDTO.setId(numberBetCard.getId());
        numberBetCardDTO.setNumber(numberBetCard.getNumber());

        return numberBetCardDTO;
    }

    /**
     * Função para conversão de DTO para @Entity.
     *
     * @param numberBetCardDTO      Parâmetro para converter NumberBetCardDTO em @Entity NumberBetCard.
     * @return                      Retorna NumberBetCard.
     */

    public static NumberBetCard mapToNumberBetCard(NumberBetCardDTO numberBetCardDTO) {
        NumberBetCard numberBetCard = new NumberBetCard();
        numberBetCard.setId(numberBetCardDTO.getId());
        numberBetCard.setNumber(numberBetCardDTO.getNumber());

        return numberBetCard;
    }
}
