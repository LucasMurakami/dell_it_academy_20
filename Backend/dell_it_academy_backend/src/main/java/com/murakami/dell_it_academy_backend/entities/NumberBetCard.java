package com.murakami.dell_it_academy_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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
@Table(name = "bet_numbers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NumberBetCard implements Serializable{

    /**
     * Atributo para identificação.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo para representação de número.
     */
    @Column(nullable = false)
    private Integer number;

    /**
     * Atributo para relação @ManyToOne entre NumberBetCard e BetCard.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private BetCard betCard;
}
