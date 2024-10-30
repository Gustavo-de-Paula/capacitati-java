package com.gustavo.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gustavo.biblioteca.model.Livro;
import com.gustavo.biblioteca.repository.LivroRepository;

/**
 * Camada responsavel por implementar as regras de negocio da aplicação e fornecer os metodos necessarios para a camada Controller.
 * 
 * A anotação @Service indica ao Spring que a classe é responsável por implementar as regras de negócios da aplicação
 * 
 * @author Gustavo Souza de Paula
 * @version 1.0
 */
@Service
public class LivroService {
	@Autowired // Faz a injeção automatica da dependencia
	private LivroRepository livroRepository;
	
	public List<Livro> findAllLivros() {
		return livroRepository.findAll();
	}
	
	public Optional<Livro> findById(Long id) {
		return livroRepository.findById(id);
	}
	
	public Optional<Livro> findByIsbn(Long isbn) {
		return livroRepository.findByIsbn(isbn);
	}
	
	public Optional<Livro> findByNome(String nome) {
		return livroRepository.findByNome(nome);
	}
	
	public List<Livro> findByAutor(String nome) {
		return livroRepository.findByAutor(nome);
	}
	
	public List<Livro> findByGenero(String genero) {
		return livroRepository.findAllByGenero(genero);
	}
	
	public Livro createLivro(Livro livro) {
		return livroRepository.save(livro);
	}
	
	public void deleteLivro(Long id) {
		livroRepository.deleteById(id);
	}
}
