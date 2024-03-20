import axios from 'axios';

// Classe que faz a comunicação com o backend para a entidade Client por meio da API_URL.

const API_URL = 'http://localhost:8080/api/clients';

export function createClient(client) {
    return axios.post(API_URL, client);
}

export function getClientById(id) {
    return axios.get(API_URL + '/id/' + id);
}

export function getAllClients() {
    return axios.get(API_URL);
}

export function getClientByCPF(cpf) {
    return axios.get(API_URL + '/cpf/' + cpf);
}

export function putBetCardsClient(id, client) {
    return axios.put(API_URL + '/id/' + id, client);
}