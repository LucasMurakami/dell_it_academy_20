package com.murakami.dell_it_academy_backend.DTOs;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Classe DTO da @Entity LuckyClient.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

@Data
public class LuckyClientDTO {

    /**
     * Atributo para identificação.
     */
    private Long id;

    /**
     * Atributo para representação de nome.
     */
    private String name;

    /**
     * Atributo para representação de cpf.
     */
    private String cpf;

    /**
     * Atributo para representação de prêmio.
     */
    private BigDecimal prize;

    /**
     * Atributo para representar a lista de betCards ganhadoras.
     */
    private BetCardDTO betCardDTO;
}
