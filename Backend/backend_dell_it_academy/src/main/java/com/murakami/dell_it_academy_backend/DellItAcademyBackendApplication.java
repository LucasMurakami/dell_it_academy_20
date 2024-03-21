package com.murakami.dell_it_academy_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe responsável por iniciar o backend.
 *
 * @author Lucas Kaito Murakami
 * @version 1.0
 */

@SpringBootApplication
public class DellItAcademyBackendApplication {

	/**
	 * Função que inicia o backend.
	 * @param args		Parâmetro necessário para rodar a Main.
	 */

	public static void main(String[] args) {
		SpringApplication.run(DellItAcademyBackendApplication.class, args);
	}

}
