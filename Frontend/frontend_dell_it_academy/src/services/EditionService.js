import axios from 'axios';

// Classe que faz a comunicação com o backend para a entidade Edition por meio da API_URL.

const API_URL = 'http://localhost:8080/api/editions';

export function createEdition(edition) {
    return axios.post(API_URL, edition);
}

export function getAllEditions() {
    return axios.get(API_URL);
}

export function getLastEdition() {
    return axios.get(API_URL + '/last');
}

export function getEditionById(id) {
    return axios.get(API_URL + '/' + id);
}

export function searchWinners() {
    return axios.get(API_URL + '/winners');
}