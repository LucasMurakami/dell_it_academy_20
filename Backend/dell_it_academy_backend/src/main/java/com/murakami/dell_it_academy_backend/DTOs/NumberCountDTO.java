package com.murakami.dell_it_academy_backend.DTOs;

import lombok.Data;

/**
 * Classe DTO da @Entity NumberBetCard.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

@Data
public class NumberCountDTO {

    /**
     * Atributo para identificação.
     */
    private Long id;

    /**
     * Atributo para representar a edição do número apostado.
     */
    private Long editionId;

    /**
     * Atributo para representação de número.
     */
    private Integer number;

    /**
     * Atributo para representar a quantidade de vezes que o número foi apostado.
     */
    private Long count;

    public NumberCountDTO(Long id, Long editionId, Integer number, Long count) {
        this.id = id;
        this.editionId = editionId;
        this.number = number;
        this.count = count;
    }
}
