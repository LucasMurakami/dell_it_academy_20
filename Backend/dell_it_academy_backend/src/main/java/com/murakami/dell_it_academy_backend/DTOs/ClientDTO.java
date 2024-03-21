package com.murakami.dell_it_academy_backend.DTOs;


import com.murakami.dell_it_academy_backend.entities.BetCard;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * Classe DTO da @Entity Client.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

@Data
public class ClientDTO {

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
     * Atributo para representação de cartelas de aposta.
     */
    private Set<BetCardDTO> betCards;
}
