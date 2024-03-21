package com.murakami.dell_it_academy_backend.mapper;

import com.murakami.dell_it_academy_backend.DTOs.BetCardDTO;
import com.murakami.dell_it_academy_backend.DTOs.LuckyClientDTO;
import com.murakami.dell_it_academy_backend.entities.BetCard;
import com.murakami.dell_it_academy_backend.entities.LuckyClient;
import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe responsável para transicionar entre DTO e Entity e vice-versa da @Entity LuckyClient para facilitar o manejamemento de dados vindos da da web ao banco de dados e vice-versa.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

public class LuckyClientMapper {

    /**
     * Função para conversão de @Entity para DTO.
     *
     * @param luckyClient           Parâmetro para converter @Entity LuckyClient em LuckyClientDTO.
     * @return                      Retorna LuckyClientDTO.
     */

    public static LuckyClientDTO mapToLuckyClientDTO(LuckyClient luckyClient) {
        LuckyClientDTO luckyClientDTO = new LuckyClientDTO();
        BeanUtils.copyProperties(luckyClient, luckyClientDTO);

        luckyClientDTO.setBetCardDTO(BetCardMapper.mapToBetCardDTO(luckyClient.getWinnerBetCards()));

        return luckyClientDTO;
    }

    /**
     * Função para conversão de DTO para @Entity.
     *
     * @param luckyClientDTO        Parâmetro para converter LuckyClientDTO em @Entity LuckyClient.
     * @return                      Retorna LuckyClient.
     */

    public static LuckyClient mapToLuckyClient(LuckyClientDTO luckyClientDTO) {
        LuckyClient luckyClient = new LuckyClient();
        BeanUtils.copyProperties(luckyClientDTO, luckyClient);
        return luckyClient;
    }
}
