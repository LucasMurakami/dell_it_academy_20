package com.murakami.dell_it_academy_backend.utils;

import com.murakami.dell_it_academy_backend.entities.BetCard;
import com.murakami.dell_it_academy_backend.entities.NumberBetCard;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Classe utilitária para encurtar funções e gerar randomificações, bem como premiar os vencedores.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */
@Component
public class UtilityClass {

    /**
     * Função para gerar números aleatórios no intervalo de 1 a 50.
     * @return          Retorna Integer com o valor entre 1 a 50.
     */
    public Integer randomNumber() {
        Random rand = new Random();
        return rand.nextInt(50) + 1;
    }

    /**
     * Função para gerar 5 números da sorte no intervalo de 1 a 50.
     * @return          Retorna Set de Integer com 5 Integer com o valor de a 1 a 50.
     */
    public Set<Integer> luckyNumbers() {
        Set<Integer> set = new HashSet<>();

        while(set.size() < 5) {
            int random = randomNumber();
            set.add(random);
        }

        return set;
    }

    /**
     * Função auxiliar da classe BetCard. Lê e retorna em Set de Integer todos os NumberBetCard alocados na BetCard.
     * @param betCard   Parâmetro para receber todos os números da BetCard.
     * @return          Retorna Set de Integer com os valores do atributo Set de NumberBetCard da BetCard.
     */

    public Set<Integer> getNumbers(BetCard betCard) {
        Set<Integer> numbers = new HashSet<>();
        for (NumberBetCard number: betCard.getNumbers() ) {
            numbers.add(number.getNumber());
        }
        return numbers;
    }

    /**
     * Função auxiliar da classe Edition. Lê um Set de Integer e retorna o Set de Integer recebido + um valor entre 1 a 50.
     * @param luckyNumbers  Parâmetro para receber os valores de luckyNumbers.
     * @return              Retorna Set de Integer com um Integer a mais (Entre 1 a 50).
     */
    public Set<Integer> addLuckyNumber(Set<Integer> luckyNumbers) {
        int luckyNumber;
        do{
            luckyNumber = randomNumber();
        } while (luckyNumbers.contains(luckyNumber));
        luckyNumbers.add(luckyNumber);
        return luckyNumbers;
    }

    /**
     * Função auxiliar da classe Edition. Recebe os parâmetros para calcular o prêmio, quanto menos rodadas maior o prêmio.
     * @param winners       Parâmetro para receber a quantidade total de ganhadores.
     * @param totalBetCards Parâmetro para receber a quantidade total de cartelas de aposta.
     * @param rounds        Parâmetro para receber a quantidade de rodadas.
     * @return              Retorna um BigDecimal com o valor do prêmio.
     */
    public BigDecimal winnerPrize(int winners, int totalBetCards, int rounds) {
        int multiplyer = 41 - rounds;
        return BigDecimal.valueOf(((long) totalBetCards * multiplyer)/winners);
    }

}
