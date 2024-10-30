package com.gustavo.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe responsavel por inicializar a aplicação.
 * 
 * A anotação @SpringBootApplication combina as funcionalidades das anotações @Configuration, @EnableAutoConfiguration e @ComponentScan e é usada para configurar
 * automaticamente os componentes do Spring com base nas dependencias do classpath.
 * 
 * @author Gustavo Souza de Paula
 * @version 1.0
 */
@SpringBootApplication
public class BibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

}
