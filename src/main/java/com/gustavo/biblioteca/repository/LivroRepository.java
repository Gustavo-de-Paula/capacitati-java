package com.gustavo.biblioteca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavo.biblioteca.model.Livro;

/**
 * Camada responsavel por fazer a comunicação da aplicação com a DB.
 * 
 * A classe herda a interface JpaRepository e contem as declarações dos metodos usados para fazer a comunicação da aplicação com a DB.
 * A anotação @Repository informa ao Spring que a classe é um repositorio de dados e é usada para acessar e manipular dados na DB.
 * 
 * @author Gustavo Souza de Paula
 * @version 1.0
 */
@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
	Optional<Livro> findByIsbn(Long isbn);
	Optional<Livro> findByNome(String nome);
	List<Livro> findByAutor(String autor);
	List<Livro> findAllByGenero(String genero);
}
