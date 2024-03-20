import axios from 'axios';

// Classe que faz a comunicação com o backend para a entidade LuckyClient por meio da API_URL.

const API_URL = 'http://localhost:8080/api/luckyclients';

export function getLuckyClientById(id) {
    return axios.get(API_URL + '/' + id);
}

export function getAllLuckyClients() {
    return axios.get(API_URL);
}
