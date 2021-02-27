package br.com.projetoIntegrador.projetoEcoIntegrador.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity

public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//talvez tirar 
	private Long idCategoria;
	
	@NotNull(message = "Por favor, insira o departamento do seu produto.")
	private String departamento;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("categoria")
	private List<Produto> produtosCategoria;
	
	public Categoria(Long idCategoria, String departamento) {
		this.idCategoria = idCategoria;
		this.departamento = departamento;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public List<Produto> getProdutosCateoria() {
		return produtosCategoria;
	}

	public void setProdutos(List<Produto> produtosCategoria) {
		this.produtosCategoria = produtosCategoria;
	}
}
