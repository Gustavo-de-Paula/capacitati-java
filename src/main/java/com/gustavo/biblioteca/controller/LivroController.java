package com.gustavo.biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Transactional;

import com.gustavo.biblioteca.model.Livro;
import com.gustavo.biblioteca.service.LivroService;

import jakarta.validation.Valid;

/**
 * Camada responsavel por gerenciar os endpoints e o fluxo de uma aplicação web e a lidar com solicitações HTTP vindas dos clientes. Tem como principal
 * responsabilidade receber as solicitacoes do cliente, processa-las e determinar qual acao deve ser tomada em resposta a essas solicitacoes utilizando os
 * metodos fornecidos pela camada de serviços.
 * 
 * @author Gustavo Souza de Paula
 * @version 1.0
 */
@RestController
@RequestMapping(path = "/biblioteca/livros")
public class LivroController {
	@Autowired
	public LivroService livroService;
	
	/**
	 * Metodo responsavel por inserir um livro novo no banco de dados.
	 * 
	 * O metodo recebe um objeto Livro como parametro e envia esse objeto para a camada de servico, para que seja feita sua persistencia no banco de dados, ao
	 * mesmo tempo que armazena o objeto persistido em uma variavel do tipo Livro para exibi-la no retorno do metodo.
	 * 
	 * A anotacao @PostMapping é utilizada para tratar requisicoes POST (criacao) de um endpoint especificado pelo atributo "path".
	 * A anotacao @Transactional garante que as operacoes efetuadas dentro do metodo sejam persistidas no banco de dados em caso de sucesso ou que seja feito o 
	 * rollback das alteracoes em caso de falha. 
	 * A anotacao @RequestBody faz a deserilizacao automatica do JSON recebido em um objeto Java enquanto @Valid valida se o JSON possui todos os campos
	 * necessarios para que esta deserializacao seja feita.
	 * 
	 * Retorna uma ResponseEntity, que representa toda a resposta HTTP retornada pelo endpoint: corpo do objeto persistido, codigo HTTP e cabeçalhos.
	 * 
	 * @param livro
	 * @return ResponseEntity
	 */
	@PostMapping(path = "/criar")
	@Transactional
	public ResponseEntity<Livro> createLivro(@RequestBody @Valid Livro livro) {
		Livro temp = livroService.createLivro(livro);
		return new ResponseEntity<>(temp, HttpStatus.CREATED);
	}
	
	/**
	 * Metodo responsavel por retornar uma lista contendo todos os Livros persisitidos no banco de dados.
	 * 
	 * O metodo armazena uma lista de objetos Livro recebida da camada de servico, e os armazena em um atributo do tipo List. Depois retorna uma ResponseEntity
	 * contendo a lista de livros e um codigo HTTP.
	 * 
	 * A anotação @GetMapping é utilizada para tratar requisicoes GET (consulta) de um endpoint. Como o endpoint não foi especificado com um atributo na 
	 * anotação, o metodo utiliza o endpoint informado no atributo "path" da anotação @RequestMapping, na declaração da classe.
	 * A anotação "readOnly" da anotação @Transactional garante que este metodo execute apenas transações de consulta.
	 * 
	 * @return ResponseEntity
	 */
	@GetMapping
	@Transactional(readOnly = true)
	public ResponseEntity<List<Livro>> findAll() {
		List<Livro> livros = livroService.findAllLivros();
		return new ResponseEntity<>(livros, HttpStatus.OK);
	}
	
	/**
	 * Metodo utilizado para pesquisar por um livro que possua um ID especifico.
	 * 
	 * O metodo recebe um Long do Cliente, consulta o Service para buscar no DB por um livro que possua o ID de acordo com o Long recebido e retorna uma
	 * ResponseEntity. Caso seja localizado um livro com o ID informado a ResponseEntity contera um objeto do tipo Livro e um codigo HTTP, caso contrario a 
	 * ResponseEntity retornara apenas um codigo HTTP.
	 * O atributo "path" da anotação @GetMapping informa que o Long sera recebido como uma variavel de URL ({id}).
	 * A anotação @PathVariable na declaração do metodo informa que o parametro sera recebido da URL do endpoint.
	 * 
	 * @param id
	 * @return ResponseEntity
	 */
	@GetMapping(path = "/id/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<Optional<Livro>> findById(@PathVariable Long id) {
		Optional<Livro> temp = livroService.findById(id);
		
		if(temp.isPresent())
			return new ResponseEntity<>(temp, HttpStatus.FOUND);
		
		return ResponseEntity.notFound().build(); 
	}
	
	/**
	 * Metodo utilizado para pesquisar por um livro que possua um ISBN especifico.
	 * 
	 * O metodo recebe um Long do Cliente, consulta o Service para buscar no DB por um livro que possua o ISBN de acordo com o Long recebido e retorna uma
	 * ResponseEntity. Caso seja localizado um livro com o ISBN informado a ResponseEntity contera um objeto do tipo Livro e um codigo HTTP, caso contrario a 
	 * ResponseEntity retornara apenas um codigo HTTP.
	 * 
	 * @param isbn
	 * @return ResponseEntity
	 */
	@GetMapping(path = "/isbn/{isbn}")
	@Transactional(readOnly = true)
	public ResponseEntity<Optional<Livro>> findByIsbn(@PathVariable Long isbn) {
		Optional<Livro> temp = livroService.findByIsbn(isbn);
		return new ResponseEntity<>(temp, HttpStatus.FOUND);
	}
	
	/**
	 * Metodo utilizado para pesquisar por um livro que possua um nome especifico.
	 * 
	 * O metodo recebe uma String do Cliente, consulta o Service para buscar no DB por um livro que possua o nome de acordo com o String recebido e retorna uma
	 * ResponseEntity. Caso seja localizado um livro com o nome informado a ResponseEntity contera um objeto do tipo Livro e um codigo HTTP, caso contrario a 
	 * ResponseEntity retornara apenas um codigo HTTP.
	 * 
	 * @param nome
	 * @return ResponseEntity
	 */
	@GetMapping(path = "/nome/{nome}")
	@Transactional(readOnly = true)
	public ResponseEntity<Optional<Livro>> findByNome(@PathVariable String nome) {
		Optional<Livro> temp = livroService.findByNome(nome.toUpperCase());
		return new ResponseEntity<>(temp, HttpStatus.FOUND);
	}
	
	/**
	 * Metodo utilizado para pesquisar por livros de um autor especifico.
	 * 
	 * O metodo recebe uma String do Cliente, consulta o Service para buscar no DB por livros que possuam o autor de acordo com o String recebido e atribui
	 * estes livros a uma lista temporaria. Depois sera retornado para o cliente uma ResponseEntity contendo a lista temporaria de livros e um codigo HTTP.
	 * 
	 * @param autor
	 * @return ResponseEntity
	 */
	@GetMapping(path = "/autor/{autor}")
	@Transactional(readOnly = true)
	public ResponseEntity<List<Livro>> findByAutor(@PathVariable String autor) {
		List<Livro> livros = livroService.findByAutor(autor.toUpperCase());
		return new ResponseEntity<>(livros, HttpStatus.FOUND);
	} 
	
	/**
	 * Metodo utilizado para pesquisar por livros de um genero especifico.
	 * 
	 * O metodo recebe uma String do Cliente, consulta o Service para buscar no DB por livros que possuam o genero de acordo com o String recebido e atribui
	 * estes livros a uma lista temporaria. Depois sera retornado para o cliente uma ResponseEntity contendo a lista temporaria de livros e um codigo HTTP.
	 * 
	 * @param genero
	 * @return ResponseEntity
	 */
	@GetMapping(path = "/genero/{genero}")
	@Transactional(readOnly = true)
	public ResponseEntity<List<Livro>> findByGenero(@PathVariable String genero) {
		List<Livro> temp = livroService.findByGenero(genero.toUpperCase());
		return new ResponseEntity<>(temp, HttpStatus.FOUND);
	}
	
	/**
	 * Metodo utilizado para atualizar os atributos de um livro ja cadastrado no DB.
	 * 
	 * O metodo recebe um Long do Cliente e um JSON convertido para um objeto do tipo Livro, consulta o Service para buscar no DB por um livro que possua o ID
	 * de acordo com o Long recebido do Cliente. Caso seja localizado um livro com o ID informado, o metodo armazena ele em objeto Livro temporario, faz a
	 * alteração dos atributos do livro temporario com os atributos do livro passado como parametro, chama o Service para persistir as alterações no DB e
	 * retorna uma ResponseEntity contendo o corpo do livro temporario e um codigo HTTP. Caso contrario, sera retornado apenas uma ResponseEntity contendo um
	 * codigo HTTP.
	 * 
	 * @param id
	 * @param livro
	 * @return ResponseEntity
	 */
	@PutMapping(path = "/update/{id}")
	@Transactional
	public ResponseEntity<Optional<Livro>> updateById(@PathVariable Long id, @RequestBody @Valid Livro livro){
		Optional<Livro> temp = livroService.findById(id);
		
		if(temp.isPresent()) {
			temp.get().setIsbn(livro.getIsbn());
			temp.get().setNome(livro.getNome());
			temp.get().setAutor(livro.getAutor());
			temp.get().setGenero(livro.getGenero());
			
			livroService.createLivro(temp.get());
			return new ResponseEntity<>(temp, HttpStatus.OK);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * Metodo utilizado para remover um livro ja persistido na DB.
	 * 
	 * O metodo recebe um Long do Cliente, consulta o Service para buscar no DB por um livro que possua o ID de acordo com o Long recebido e armazena este livro
	 * em uma variavel temporaria.
	 * Caso seja localizado um livro com o ID informado, sera feita a chamada do metodo equivalente do Service, passando o id do livro temporario como parametro
	 * e retornando uma ResponseEntity com um codigo HTTP. Caso contrario, sera retornado uma outra ResponseEntity com um outro codigo HTTP.
	 * 
	 * @param id
	 * @return ResponseEntity
	 */
	@DeleteMapping(path = "/delete/{id}")
	@Transactional
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		Optional<Livro> temp = livroService.findById(id);
		
		if(temp.isPresent()) {
			livroService.deleteLivro(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return ResponseEntity.notFound().build();
	} 
}
