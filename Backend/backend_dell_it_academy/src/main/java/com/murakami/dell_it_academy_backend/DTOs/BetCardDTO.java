package com.murakami.dell_it_academy_backend.DTOs;


import com.murakami.dell_it_academy_backend.entities.NumberBetCard;
import lombok.Data;

import java.util.Set;

/**
 * Classe DTO da @Entity BetCard.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

@Data
public class BetCardDTO {

    /**
     * Atributo para identificação.
     */
    private Long id;

    /**
     * Atributo para representar o Identificador de uma @Entity Client.
     */
    private Long clientId;

    /**
     * Atributo para representar o Identificador de uma @Entity Edition.
     */
    private Long editionId;

    /**
     * Atributo para representar os números da sorte de uma cartela.
     */
    private Set<NumberBetCard> numbers;
}
