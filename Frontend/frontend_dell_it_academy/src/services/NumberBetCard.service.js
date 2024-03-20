import axios from 'axios';

// Clase que faz a comunicação com o backend para a entidade NumberBetCard por meio da API_URL.

const API_URL = 'http://localhost:8080/api/numberBetcards';


// Função que cria uma nova BetCard no backend. 
export function createNumberBetCard(numberBetCard) {
    return axios.post(API_URL, numberBetCard);
}

// Função que recebe do Backend a lista de números apostados em uma edição.

export function findHighestNumbersByEditionId (editionId) {
    return axios.get(API_URL + '/highest/numbersBet/' + editionId);
}