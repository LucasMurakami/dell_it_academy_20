package com.murakami.dell_it_academy_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "lucky_clients")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LuckyClient implements Serializable {

    /**
     * Atributo para identificação.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo para representação do nome.
     */
    @Column(nullable = false)
    String name;

    /**
     * Atributo para representação do cpf.
     */
    @Column(nullable = false, length = 11)
    String cpf;

    /**
     * Atributo para representação do prêmio.
     */
    @Column
    BigDecimal prize;

    /**
     * Atributo para relação @OneToMany entre LuckyClient e BetCard
     */
    @OneToOne(fetch = FetchType.LAZY)
    BetCard winnerBetCards;

    /**
     * Construtor personalizado para criação de um LuckyClient.
     * @param client    Cliente que teve uma cartela sorteada.
     * @param betCard   Cartela que o cliente ganhou.
     */
    public LuckyClient(Client client, BetCard betCard) {
        this.name = client.name;
        this.cpf = client.cpf;
        this.winnerBetCards = betCard;
    }
}
