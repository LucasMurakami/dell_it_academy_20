package com.murakami.dell_it_academy_backend.mapper;


import com.murakami.dell_it_academy_backend.DTOs.ClientDTO;
import com.murakami.dell_it_academy_backend.DTOs.ClientPutBetCardDTO;
import com.murakami.dell_it_academy_backend.entities.BetCard;
import com.murakami.dell_it_academy_backend.entities.Client;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Classe responsável para transicionar entre DTO e Entity e vice-versa da @Entity Client para facilitar o manejamemento de dados vindos da da web ao banco de dados e vice-versa.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */


public class ClientMapper {

    /**
     * Função para conversão de @Entity para DTO.
     *
     * @param client            Parâmetro para converter @Entity Client em ClientDTO.
     * @return                  Retorna ClientDTO.
     */
    public static ClientDTO mapToClientDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        if(client != null) {
            clientDTO.setId(client.getId());
            clientDTO.setName(client.getName());
            clientDTO.setCpf(client.getCpf());
            if(client.getBetCards() != null)
                clientDTO.setBetCards(new ArrayList<>(client.getBetCards()));
            else
                clientDTO.setBetCards(new ArrayList<>());
        }
        return clientDTO;
    }

    /**
     * Função para conversão de DTO para @Entity.
     *
     * @param clientDTO         Parâmetro para converter ClientDTO em @Entity Client.
     * @return                  Retorna Client.
     */

    public static Client mapToClient(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setName(clientDTO.getName());
        client.setCpf(clientDTO.getCpf());
        if(clientDTO.getBetCards() != null)
            client.setBetCards(new HashSet<>(clientDTO.getBetCards()));
        else
            client.setBetCards(new HashSet<>());

        return client;
    }

    public static ClientPutBetCardDTO mapToClientPutBetCardDTO(Client client) {
        ClientPutBetCardDTO clientPutBetCardDTO = new ClientPutBetCardDTO();

        clientPutBetCardDTO.setId(client.getId());

        BetCard betcardHighestId = new BetCard();

        if(client.getBetCards() != null) {
            int maxId = 0;
            for (BetCard betcard: client.getBetCards()) {
                if(betcard.getId() > maxId) {
                    maxId = Math.toIntExact(betcard.getId());
                    betcardHighestId = betcard;
                }
            }
        }

        clientPutBetCardDTO.setBetCardDTO(BetCardMapper.mapToBetCardDTO(betcardHighestId));
        return clientPutBetCardDTO;
    }

    public static Client mapToClientFromClientPutBetCardDTO(ClientPutBetCardDTO clientPutBetCardDTO) {
        Client client = new Client();

        client.getBetCards().add(BetCardMapper.mapToBetCard(clientPutBetCardDTO.getBetCardDTO()));

        return client;
    }
}
