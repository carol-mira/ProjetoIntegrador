package br.com.projetoIntegrador.projetoEcoIntegrador.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.projetoIntegrador.projetoEcoIntegrador.model.Categoria;



@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	public List<Categoria> findAllByDepartamentoContainingIgnoreCase(String departamento);
	
}
