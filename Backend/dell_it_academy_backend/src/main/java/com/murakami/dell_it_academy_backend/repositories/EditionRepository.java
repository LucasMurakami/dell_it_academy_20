package com.murakami.dell_it_academy_backend.repositories;

import com.murakami.dell_it_academy_backend.entities.Edition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Classe responsável por lidar com o banco de dados.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

public interface EditionRepository extends JpaRepository<Edition, Long> {

    /**
     *  Função que busca a última Edition do banco de dados.
     * @return      Retorna a última Edition do banco de dados.
     */
    @Query(value = "SELECT * FROM EDITIONS ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Edition findLast();
}
