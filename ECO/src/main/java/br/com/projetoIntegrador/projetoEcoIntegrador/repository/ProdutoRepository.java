package br.com.projetoIntegrador.projetoEcoIntegrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projetoIntegrador.projetoEcoIntegrador.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Long>{
	public List<Produto> findAllByNomeProdutoContainingIgnoreCase(String nomeProduto);	
	
	
	
	
	@Query(value = "SELECT * FROM produto"
			+ " INNER JOIN categoria"
			+ " ON produto.categoria_id = categoria.id_categoria"
			+ " WHERE categoria.departamento = :departamento"
			+ " AND produto.nome_produto = :nomeProduto", nativeQuery = true)
	public List<Produto> findAllBatatinhas(@Param("departamento") String departamento, @Param("nomeProduto") String nomeProduto);
	

}
