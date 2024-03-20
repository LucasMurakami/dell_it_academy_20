package com.murakami.dell_it_academy_backend.repositories;

import com.murakami.dell_it_academy_backend.DTOs.NumberCountDTO;
import com.murakami.dell_it_academy_backend.entities.NumberBetCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Classe responsável por lidar com o banco de dados.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

public interface NumberBetCardRepository extends JpaRepository<NumberBetCard, Long> {

    @Query(value = "SELECT bn.NUMBER, COUNT(bn.NUMBER) AS count\n" +
            "FROM BET_NUMBERS bn\n" +
            "INNER JOIN BET_CARDS bc ON bn.BET_CARD_ID = bc.ID\n" +
            "INNER JOIN EDITIONS e ON bc.EDITION_ID = e.ID\n" +
            "WHERE e.ID = :editionId\n" +
            "GROUP BY e.ID, bn.NUMBER", nativeQuery = true)
    List<Object[]> findHighestNumbersByEditionId(@Param("editionId") Long editionId);
}
