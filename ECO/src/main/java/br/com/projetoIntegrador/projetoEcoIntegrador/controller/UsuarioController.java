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

	@GetMapping("/{cpf}")
	public ResponseEntity<Usuario> getByCpf(@PathVariable String cpf) {
		return usuarioRepository.findById(cpf).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nomeUsuario/{nomeUsuario}") 
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
	
	@GetMapping("/meusFavoritos/{meusFavoritos}")
	public ResponseEntity<List<Usuario>> getByMeusFavoritos(@PathVariable String meusFavoritos) {
		return ResponseEntity.ok(usuarioRepository.findAllByMeusFavoritosContainingIgnoreCase(meusFavoritos));
	}
	
	@GetMapping("/minhasCompras/{minhasCompras}")
	public ResponseEntity<List<Usuario>> getByMinhasCompras(@PathVariable String minhasCompras) {
		return ResponseEntity.ok(usuarioRepository.findAllByMinhasComprasContainingIgnoreCase(minhasCompras));
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

	@PostMapping("/produto/novo/{cpf}")
	public ResponseEntity<?> novoProduto(
			@PathVariable(value = "cpf") String cpf,
			@Valid @RequestBody Produto novoProduto){
		Produto cadastro =  usuarioService.cadastrarProduto(novoProduto, cpf);
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
	
	@PutMapping("/produto/compra/{id_Produto}/{cpf}")
	public ResponseEntity<?> novaCompra(
			@PathVariable(value = "id_Produto") Long idProduto,
			@PathVariable(value = "cpf") String cpf){
		Usuario compra = usuarioService.comprarProduto(cpf, idProduto);
		if(compra == null) {
			return new ResponseEntity<String>("Produto ou Usuario invalido", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Usuario>(compra, HttpStatus.CREATED);
	}

	@PutMapping("/alterar/senha")
	public ResponseEntity<?> alterarSenha(@Valid @RequestBody Usuario usuario) {
		Optional<Usuario> usuarioAlterado = usuarioService.atualizarUsuario(usuario);
		if (usuarioAlterado.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioAlterado.get());
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha na atualização!");
		}
	}
	
	@PutMapping("/produto/favoritar/{id_Produto}/{cpf}")
	public ResponseEntity<?> novoFavorito(
			@PathVariable(value = "id_Produto") Long idProduto,
			@PathVariable(value = "cpf") String cpf){
		Usuario favorito = usuarioService.favoritar(cpf, idProduto);
		if(favorito == null) {
			return new ResponseEntity<String>("Produto ou usuário inválido", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Usuario>(favorito, HttpStatus.CREATED);
	}
	
	

	@DeleteMapping("/{cpf}")
	public void delete(@PathVariable String cpf) {
		usuarioRepository.deleteById(cpf);
	}
	
	@DeleteMapping("/produto/delete/{id_Produto}/{cpf}")
	public ResponseEntity<?> removerProduto(
			@PathVariable(value = "id_Produto") Long idProduto,
			@PathVariable(value = "cpf") String cpf){
		Usuario retorno = usuarioService.deletarProduto(idProduto, cpf);
		if(retorno == null) {
			return new ResponseEntity<String>("Produto ou usuário inválido", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Usuario>(retorno, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/produto/desfavoritar/{id_Produto}/{cpf}")
	public ResponseEntity<?> desfavoritar(
			@PathVariable(value = "id_Produto") Long idProduto,
			@PathVariable(value = "cpf") String cpf){
		Usuario retorno = usuarioService.desfavoritar(cpf, idProduto);
		if(retorno == null) {
			return new ResponseEntity<String>("Produto ou usuário inválido", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Usuario>(retorno, HttpStatus.ACCEPTED);
	}
	
}