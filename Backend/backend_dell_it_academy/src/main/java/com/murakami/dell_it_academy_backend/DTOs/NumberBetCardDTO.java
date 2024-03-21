package com.murakami.dell_it_academy_backend.DTOs;

import lombok.Data;

/**
 * Classe DTO da @Entity NumberBetCard.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

@Data
public class NumberBetCardDTO {

    /**
     * Atributo para identificação.
     */
    private Long id;

    /**
     * Atributo para representação de número.
     */
    private Integer number;

    /**
     * Atributo para representar o Identificador de uma @Entity BetCard.
     */
    private Long betCardId;
}
