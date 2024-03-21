package com.murakami.dell_it_academy_backend.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe de representação.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "editions")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Edition implements Serializable {

    /**
     * Atributo para identificação.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo para relação @OneToMany entre Edition e BetCard.
     */
    @OneToMany
    private Set<BetCard> betCards = new HashSet<>();

    /**
     * Atributo para representar os números da sorte da edição.
     */
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Integer> luckyNumbers = new HashSet<>();

    /**
     * Atributo para relação @OneToMany entre Edition e LuckyClient.
     */
    @OneToMany(fetch = FetchType.LAZY)
    private Set<LuckyClient> luckyClients = new HashSet<>();

    /**
     * Atributo para representar a quantia total de rodadas até achar um vencedor ou até encerrar o sorteio.
     */
    @Column
    private Integer lotteryRounds;

    /**
     * Atributo para representar a quantia total de apostas vencedoras.
     */
    private Integer luckyBets;

    /**
     * Atributo para representar se a edição está ativa ou não.
     */
    private Boolean isActive;

}
