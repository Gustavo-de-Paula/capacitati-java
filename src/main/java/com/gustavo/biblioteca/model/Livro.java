package com.gustavo.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Camada responsavel por implementar as entidades que serão usadas na criação das tabelas da DB.
 * 
 * A anotação @Entity informa ao Spring que a classe devera ser mapeada a uma tabela na DB enquanto a anotação @Table é usada para atribuir um nome a esta tabela,
 * utilizando o atributo "name".
 * 
 * @author Gustavo Souza de Paula
 * @version 1.0
 */
@Entity
@Table(name = "livro")
public class Livro {
	@Id // informa ao Hibernate que este atributo sera utilzado como chave primaria da tabela.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Instrui o Hibernate a gerar, automaticamente, valores unicos para o atributo.
	private Long id;
	
	@Column(unique = true) // Atribui comportamentos a coluna no momento de sua criação: valor deve ser unico.
	@NotNull // Indica que o atributo não pode ser null
	private Long isbn;
	
	@NotBlank(message = "Informe o nome do livro.") // Indica que o atributo não pode ser nulo e remove espaços em branco no começo e final da String
	private String nome;
	
	@NotBlank(message = "Informe o autor do livro.") // O atributo "message" informa a mensagem que deve ser retornada caso de violação da regra
	private String autor;
	
	@NotBlank(message = "Informe o genero do livro.")
	private String genero;
	
	public Livro() {}
	public Livro (Long isbn, String nome, String autor, String genero) {
		this.isbn = isbn;
		this.nome = nome;
		this.autor = autor;
		this.genero = genero;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + "\nISBN: " + isbn + "\nNome: " + nome + "\nAutor: " + autor + "\nGenero: " + genero;
	}

	public Long getId() {
		return id;
	}
	public Long getIsbn() {
		return isbn;
	}
	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
}
