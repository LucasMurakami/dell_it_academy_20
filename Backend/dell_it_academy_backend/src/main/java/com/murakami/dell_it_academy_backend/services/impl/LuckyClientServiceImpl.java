package com.murakami.dell_it_academy_backend.services.impl;

import com.murakami.dell_it_academy_backend.DTOs.LuckyClientDTO;
import com.murakami.dell_it_academy_backend.entities.LuckyClient;
import com.murakami.dell_it_academy_backend.exceptions.ResourceNotFoundException;
import com.murakami.dell_it_academy_backend.mapper.LuckyClientMapper;
import com.murakami.dell_it_academy_backend.repositories.LuckyClientRepository;
import com.murakami.dell_it_academy_backend.services.LuckyClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Classe responsável pela lógica de negócio ao respeito da @Entity LuckyClient.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

@Service
@AllArgsConstructor
public class LuckyClientServiceImpl implements LuckyClientService {

    /**
     * Atributo para interação com o banco de dados na tabela lucky_clients na camada Repository.
     */
    private LuckyClientRepository luckyClientRepository;

    /**
     * Função para criar um luckyClient no banco de dados por meio de dados da WEB na camada Repository. Atualiza a tabela lucky_clients.
     * @param luckyClientDTO    Parâmetro para recebimento do luckyClientDTO que contém as informações para criação do luckyClient no banco de dados.
     * @return                  Retorna LuckyClientDTO do LuckyClient salvo no banco de dados para posteriormente informar a camada Controller que o objeto foi criado com sucesso ou que houve uma falha.
     */
    @Override
    @Transactional
    public LuckyClientDTO createLuckyClient(LuckyClientDTO luckyClientDTO) {
        LuckyClient luckyClient = LuckyClientMapper.mapToLuckyClient(luckyClientDTO);
        LuckyClient savedLuckyClient = luckyClientRepository.save(luckyClient);
        return LuckyClientMapper.mapToLuckyClientDTO(savedLuckyClient);
    }

    /**
     * Função para buscar um LuckyClient no banco de dados pelo ID na camada Repository.
     * @param luckyClientId     Parâmetro para recebimento do ID do LuckyClient a ser procurado.
     * @return                  Retorna LuckyClientDTO do LuckyClient encontrado no banco de dados, caso não encontre lança a exceção @ResourceNotFoundException.
     */
    @Override
    @Transactional
    public LuckyClientDTO getLuckyClientById(Long luckyClientId) {
        LuckyClient luckyClient = luckyClientRepository.findById(luckyClientId).orElseThrow(
                () -> new ResourceNotFoundException("Lucky Client not found with the id " + luckyClientId)
        );

        return LuckyClientMapper.mapToLuckyClientDTO(luckyClient);
    }

    /**
     * Função para buscar todos luckyClients no banco de dados na camada Repository.
     * @return                  Retorna Lista de LuckyClientDTO dos luckyClients encontrados no banco de dados, caso a Lista seja vazia lança a exceção @ResourceNotFoundExcpetion.
     */
    @Override
    @Transactional
    public List<LuckyClientDTO> getAllLuckyClients() {
        List<LuckyClientDTO> luckyClientList = luckyClientRepository.findAll().stream().map(LuckyClientMapper::mapToLuckyClientDTO).toList();
        if(luckyClientList.isEmpty()) throw new ResourceNotFoundException("No winners found.");
        return luckyClientList;
    }

    @Override
    @Transactional
    public List<LuckyClientDTO> getAllLuckyClientsByEdition() {
        return null;
    }
}
