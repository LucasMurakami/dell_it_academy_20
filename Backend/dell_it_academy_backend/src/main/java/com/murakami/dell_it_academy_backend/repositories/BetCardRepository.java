package com.murakami.dell_it_academy_backend.repositories;

import com.murakami.dell_it_academy_backend.entities.BetCard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Classe responsável por lidar com o banco de dados.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

public interface BetCardRepository extends JpaRepository<BetCard, Long> {
}
