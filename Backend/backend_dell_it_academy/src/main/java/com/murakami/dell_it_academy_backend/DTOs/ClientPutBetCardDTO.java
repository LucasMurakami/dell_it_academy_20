package com.murakami.dell_it_academy_backend.DTOs;

import lombok.Data;

/**
 * Classe DTO para transferência de dados.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

@Data
public class ClientPutBetCardDTO {

    /**
     * Atributo para identificação.
     */
    private Long id;

    /**
     * Atributo para representação de cartela de aposta.
     */
    private BetCardDTO betCardDTO;
}
