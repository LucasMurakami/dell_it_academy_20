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

    /**
     * Função que busca Clients pelo CPF, como há possibilidade de existir clientes com o mesmo cpf, porém, nomes e BetCards diferentes, a função retorna uma lista de Clients.
     * @param cpf           Parâmetro para recebimento do CPF.
     * @return              Retorna uma lista de Clients pelo CPF.
     */
    List<Client> findClientByCpfEquals(String cpf);
}
