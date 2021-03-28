package br.com.projetoIntegrador.projetoEcoIntegrador.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity

public class Produto {
	// long = chave primaria
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Apagou o 2, mas continuou a contagem
	private Long idProduto; // int
	
	@NotNull(message = "Por favor, informe seu produto.")
	private String nomeProduto;
	
	@NotNull(message = "Por favor, informe o preço do produto.")
	private Double preco; //D ou d 
	
	@NotNull(message = "Uma descrição é importante para obter confiança em seu produto. Que tal descrevê-lo ? ")
	@Size(min = 20 , max = 500)
	private String descricao;
    
	@ManyToOne
	@JsonIgnoreProperties("produtosCategoria")
	private Categoria categoria; //primeiro anexo de chave secundaria 
	
	@ManyToOne
	@JsonIgnoreProperties("produtosUsuario")
	private Usuario usuario; //segundo anexo de chave secundaria 
	
	@ManyToMany(mappedBy = "meusFavoritos", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"senha", "meusFavoritos", "produtosUsuario"})
	private List<Usuario> favoritadoPor = new ArrayList<>();
	
	@ManyToMany(mappedBy = "minhasCompras", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"senha", "meusProdutos", "minhasCompras"})
	private List<Usuario> compradoPor = new ArrayList<>();
		
	//Video sobre o produto 
	
	public Produto() {
	}

	public Produto(Long idProduto, String nomeProduto, Double preco, String descricao, Categoria categoria, Usuario usuario, List<Usuario> favoritadoPor) {
		
		this.idProduto = idProduto;
		this.nomeProduto = nomeProduto;
		this.preco = preco;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
		this.favoritadoPor = favoritadoPor;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getFavoritadoPor() {
		return favoritadoPor;
	}

	public void setFavoritadoPor(List<Usuario> favoritadoPor) {
		this.favoritadoPor = favoritadoPor;
	}
	
	
	
	
	
    

	
	

}
