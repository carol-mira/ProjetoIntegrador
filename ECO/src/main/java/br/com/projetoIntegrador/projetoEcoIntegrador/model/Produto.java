package br.com.projetoIntegrador.projetoEcoIntegrador.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity

public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduto; 

	@NotNull(message = "Por favor, informe seu produto.")
	private String nomeProduto;

	@NotNull(message = "Por favor, informe o preço do produto.")
	private Double preco; 

	@NotNull(message = "Uma descrição é importante para obter confiança em seu produto. Que tal descrevê-lo ? ")
	@Size(min = 20, max = 500)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	@NotNull (message = "Por favor, Inclua uma categoria.")
	@JsonIgnoreProperties({"produtosCategoria"})
	private Categoria categoria;

	@ManyToOne
	@JsonIgnoreProperties({"produtosUsuario","senhaUsuario"})
	private Usuario usuario;

	@OneToMany(mappedBy = "meusFavoritos", cascade = CascadeType.REMOVE)
	//@JsonIgnoreProperties({"senhaUsuario","meusFavoritos", "produtosUsuario", "meusProdutos" })
	private List<Usuario> favoritadoPor = new ArrayList<>();

	@OneToMany(mappedBy = "minhasCompras", cascade = CascadeType.REMOVE)
	//@JsonIgnoreProperties({"senhaUsuario","meusProdutos", "minhasCompras", "meusFavoritos" })
	private List<Usuario> compradoPor = new ArrayList<>();

	@NotNull
	private String fotoProduto1;
	
	private String fotoProduto2;
	
	private String fotoProduto3;
	
	private String fotoProduto4;
	
	private String videoProduto;


	public Produto() {
	}

	public Produto(Long idProduto, String nomeProduto, Double preco, String descricao, String fotoProduto1, 
			String fotoProduto2, String fotoProduto3, String fotoProduto4, String videoProduto, 
			Categoria categoria, Usuario usuario) {
		this.idProduto = idProduto;
		this.nomeProduto = nomeProduto;
		this.preco = preco;
		this.descricao = descricao;
		this.fotoProduto1 = fotoProduto1;
		this.fotoProduto2 = fotoProduto2;
		this.fotoProduto3 = fotoProduto3;
		this.fotoProduto4 = fotoProduto4;
		this.videoProduto = videoProduto;
		this.categoria = categoria;
		this.usuario = usuario;
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

	public String getFotoProduto1() {
		return fotoProduto1;
	}

	public void setFotoProduto1(String fotoProduto1) {
		this.fotoProduto1 = fotoProduto1;
	}

	public String getFotoProduto2() {
		return fotoProduto2;
	}

	public void setFotoProduto2(String fotoProduto2) {
		this.fotoProduto2 = fotoProduto2;
	}

	public String getFotoProduto3() {
		return fotoProduto3;
	}

	public void setFotoProduto3(String fotoProduto3) {
		this.fotoProduto3 = fotoProduto3;
	}

	public String getFotoProduto4() {
		return fotoProduto4;
	}

	public void setFotoProduto4(String fotoProduto4) {
		this.fotoProduto4 = fotoProduto4;
	}

	public String getVideoProduto() {
		return videoProduto;
	}

	public void setVideoProduto(String videoProduto) {
		this.videoProduto = videoProduto;
	}

	public List<Usuario> getFavoritadoPor() {
		return favoritadoPor;
	}

	public void setFavoritadoPor(List<Usuario> favoritadoPor) {
		this.favoritadoPor = favoritadoPor;
	}

	public List<Usuario> getCompradoPor() {
		return compradoPor;
	}

	public void setCompradoPor(List<Usuario> compradoPor) {
		this.compradoPor = compradoPor;
	}

}