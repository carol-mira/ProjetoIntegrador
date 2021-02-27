package br.com.projetoIntegrador.projetoEcoIntegrador.Controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoIntegrador.projetoEcoIntegrador.Repository.CategoriaRepository;
import br.com.projetoIntegrador.projetoEcoIntegrador.model.Categoria;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public ResponseEntity<List<Categoria>> getAll() {
		return ResponseEntity.ok(categoriaRepository.findAll());
	}

	@GetMapping("/{idCategoria}")
	public ResponseEntity<Categoria> getById(@PathVariable long idCategoria) {
		return categoriaRepository.findById(idCategoria).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/departamento/{departamento}") // falar com Boaz
	public ResponseEntity<List<Categoria>> getByDepartamento(@PathVariable String departamento) {
		return ResponseEntity.ok(categoriaRepository.findAllByDepartamentoContainingIgnoreCase(departamento));
	}

	@PostMapping
	public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria));
	}

	@DeleteMapping("/{idCategoria}")
	public ResponseEntity<List<Categoria>> delete(@PathVariable Long idCategoria) {
		categoriaRepository.deleteById(idCategoria);
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	// @DeleteMapping("/{idCategoria}")
	// public ResponseEntity delete(@PathVariable Long idCategoria) {
	// categoriaRepository.deleteById(idCategoria);
	// }
}
