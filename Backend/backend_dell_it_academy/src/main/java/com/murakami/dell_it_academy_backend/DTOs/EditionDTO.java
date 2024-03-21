package com.murakami.dell_it_academy_backend.DTOs;

import com.murakami.dell_it_academy_backend.entities.BetCard;
import com.murakami.dell_it_academy_backend.entities.LuckyClient;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe DTO da @Entity Edtion.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

@Data
public class EditionDTO {

    /**
     * Atributo para identificação.
     */
    private Long id;

    /**
     * Atributo para controle de cartelas da sorte.
     */
    private Set<BetCard> betCards;

    /**
     * Atributo para controle de números da sorte.
     */
    private Set<Integer> luckyNumbers = new HashSet<>();

    /**
     * Atributo para controle de clientes que acertaram os números da cartela da sorte.
     */
    private Set<LuckyClient> luckyClients = new HashSet<>();

    /**
     * Atributo para controle de rodadas até achar um vencedor ou o sorteio acabar.
     */
    private Integer lotteryRounds;

    /**
     * Atributo para controle de cartelas vencedoras.
     */
    private Integer luckyBets;


    /**
     * Atributo para controle de ativação da edição.
     */
    private Boolean isActive;
}
