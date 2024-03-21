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
@Table(name = "bet_cards")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BetCard implements Serializable {

    /**
     * Atributo para identificação.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "thousand_seq")
    @SequenceGenerator(name = "thousand_seq", initialValue = 1000, allocationSize = 1)
    private Long id;

    /**
     * Atributo para relação @OneToMany entre BetCard e NumberBetCard.
     */
    @OneToMany(fetch = FetchType.LAZY)
    private Set<NumberBetCard> numbers = new HashSet<>();

    /**
     * Atributo para relação @ManyToOne entre BetCard e Client.
     */
    @ManyToOne
    private Client client;

    /**
     * Atributo para relação @ManyToOne entre BetCard e Edition.
     */
    @ManyToOne
    private Edition edition;

    /**
     * Atributo para relação @ManyToOne entre BetCard e LuckyClient.
     */
    @OneToOne
    private LuckyClient luckyClient;

}
