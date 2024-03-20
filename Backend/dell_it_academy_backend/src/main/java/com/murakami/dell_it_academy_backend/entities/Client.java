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
import java.util.List;
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
@Table(name = "clients")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Client implements Serializable {

    /**
     * Atributo para identificação.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Atributo para representação de nome.
     */
    @Column(nullable = false)
    String name;

    /**
     * Atributo para representação de cpf.
     */
    @Column(nullable = false)
    String cpf;

    /**
     * Atributo para relação @OneToMany entre Client e BetCards.
     */
    @OneToMany
    Set<BetCard> betCards;
}
