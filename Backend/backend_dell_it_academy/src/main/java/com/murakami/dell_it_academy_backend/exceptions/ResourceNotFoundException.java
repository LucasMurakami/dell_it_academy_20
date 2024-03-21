package com.murakami.dell_it_academy_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe responsável para retornar exceções do backend.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    /**
     * Função que retorna uma mensagem e um HttpStatus NOT_FOUND.
     * @param message   Mensagem que indica o status de um recurso não encontrado.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
