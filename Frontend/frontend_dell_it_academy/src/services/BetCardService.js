import axios from 'axios';

// Classe que faz a comunicação com o backend para a entidade BetCard por meio da API_URL.

const API_URL = 'http://localhost:8080/api/betcards';

export function createBetCard(betCard) {
    return axios.post(API_URL, betCard);
}

export function getAllBetCards() {
    return axios.get(API_URL);
}