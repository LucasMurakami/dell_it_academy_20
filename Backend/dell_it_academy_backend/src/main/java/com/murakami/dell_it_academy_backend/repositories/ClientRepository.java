package com.murakami.dell_it_academy_backend.repositories;

import com.murakami.dell_it_academy_backend.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Classe responsável por lidar com o banco de dados.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findClientByCpfEquals(String cpf);
}
