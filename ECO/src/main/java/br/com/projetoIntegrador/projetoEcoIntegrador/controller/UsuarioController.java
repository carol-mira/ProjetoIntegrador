package br.com.projetoIntegrador.projetoEcoIntegrador.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoIntegrador.projetoEcoIntegrador.model.Produto;
import br.com.projetoIntegrador.projetoEcoIntegrador.model.Usuario;
import br.com.projetoIntegrador.projetoEcoIntegrador.model.UsuarioLogin;
import br.com.projetoIntegrador.projetoEcoIntegrador.repository.UsuarioRepository;
import br.com.projetoIntegrador.projetoEcoIntegrador.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}

	@GetMapping("/{idUsuario}")
	public ResponseEntity<Usuario> getByCpf(@PathVariable String idUsuario) {
		return usuarioRepository.findById(idUsuario).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nomeUsuario/{nomeUsuario}") // pesquisar
	public ResponseEntity<List<Usuario>> getByNomeUsuario(@PathVariable String nomeUsuario) {
		return ResponseEntity.ok(usuarioRepository.findAllByNomeUsuarioContainingIgnoreCase(nomeUsuario));
	}

	@GetMapping("/nomeSocial/{nomeSocial}")
	public ResponseEntity<List<Usuario>> getByNomeSocial(@PathVariable String nomeSocial) {
		return ResponseEntity.ok(usuarioRepository.findAllByNomeSocialContainingIgnoreCase(nomeSocial));
	}

	@GetMapping("/nomeCompletoUsuario/{nomeCompletoUsuario}")
	public ResponseEntity<List<Usuario>> getByNomeCompletoUsuario(@PathVariable String nomeCompletoUsuario) {
		return ResponseEntity
				.ok(usuarioRepository.findAllByNomeCompletoUsuarioContainingIgnoreCase(nomeCompletoUsuario));
	}

	@GetMapping("/emailUsuario/{emailUsuario}") //
	public ResponseEntity<List<Usuario>> getByEmailUsuario(@PathVariable String emailUsuario) {
		return ResponseEntity.ok(usuarioRepository.findAllByEmailUsuarioContainingIgnoreCase(emailUsuario));
	}

	@GetMapping("/produtosUsuario/{produtosUsuario}")
	public ResponseEntity<List<Usuario>> getByProdutosUsuario(@PathVariable String produtosUsuario) {
		return ResponseEntity.ok(usuarioRepository.findAllByProdutosUsuarioContainingIgnoreCase(produtosUsuario));
	}

	@PostMapping

	public ResponseEntity<?> post(@Valid @RequestBody Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarioRepository.findById(usuario.getCpf());
		if(usuarioExistente.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(usuarioExistente.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
		}
	}
	

	@PostMapping("/produto/novo/{id_Usuario}")
	public ResponseEntity<?> novoProduto(
			@PathVariable(value = "id_Usuario") String idUsuario,
			@Valid @RequestBody Produto novoProduto){
		Produto cadastro =  usuarioService.cadastrarProduto(novoProduto, idUsuario);
		if(cadastro == null) {
			return new ResponseEntity<String>("Falha no cadastro", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Produto>(cadastro, HttpStatus.CREATED);
	}
	
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> Authentication(@RequestBody Optional<UsuarioLogin> user) {
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Cadastration(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.CadastrarUsuario(usuario));
	}
	
	@PutMapping("/produto/compra/{id_Produto}/{id_Usuario}")
	public ResponseEntity<?> novaCompra(
			@PathVariable(value = "id_Produto") Long idProduto,
			@PathVariable(value = "id_Usuario") String idUsuario){
		Usuario compra = usuarioService.comprarProduto(idUsuario, idProduto);
		if(compra == null) {
			return new ResponseEntity<String>("Produto ou Usuario invalido", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Usuario>(compra, HttpStatus.CREATED);
	}

	@PutMapping // Obs,executa um serviço nesse caso put(colocar)
	public ResponseEntity<Usuario> put(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuario));
	}
	
	@PutMapping("/produto/favoritar/{id_Produto}/{id_Usuario}")
	public ResponseEntity<?> novoFavorito(
			@PathVariable(value = "id_Produto") Long idProduto,
			@PathVariable(value = "id_Usuario") String idUsuario){
		Usuario favorito = usuarioService.favoritar(idUsuario, idProduto);
		if(favorito == null) {
			return new ResponseEntity<String>("Produto ou usuário inválido", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Usuario>(favorito, HttpStatus.CREATED);
	}

	@DeleteMapping("/{idUsuario}")
	public void delete(@PathVariable String idUsuario) {
		usuarioRepository.deleteById(idUsuario);
	}
	
	@DeleteMapping("/produto/delete/{id_Produto}/{id_Usuario}")
	public ResponseEntity<?> removerProduto(
			@PathVariable(value = "id_Produto") Long idProduto,
			@PathVariable(value = "id_Usuario") String idUsuario){
		Usuario retorno = usuarioService.deletarProduto(idProduto, idUsuario);
		if(retorno == null) {
			return new ResponseEntity<String>("Produto ou usuário inválido", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Usuario>(retorno, HttpStatus.ACCEPTED);
	}
	
}