package br.com.projetoIntegrador.projetoEcoIntegrador.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioLogin {

	private String cpf;
	private String nomeSocial;
	private String nomeCompletoUsuario;
	private String nomeUsuario;
	private Date dataAniversario;
	private String emailUsuario;
	private String senhaUsuario;
	private Integer contadorArvore = 0;
	private List<Produto> produtosUsuario;
	private List<Produto> meusFavoritos = new ArrayList<>();
	private List<Produto> minhasCompras = new ArrayList<>();
	private String Token;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeSocial() {
		return nomeSocial;
	}

	public void setNomeSocial(String nomeSocial) {
		this.nomeSocial = nomeSocial;
	}

	public String getNomeCompletoUsuario() {
		return nomeCompletoUsuario;
	}

	public void setNomeCompletoUsuario(String nomeCompletoUsuario) {
		this.nomeCompletoUsuario = nomeCompletoUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Date getDataAniversario() {
		return dataAniversario;
	}

	public void setDataAniversario(Date dataAniversario) {
		this.dataAniversario = dataAniversario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public Integer getContadorArvore() {
		return contadorArvore;
	}

	public void setContadorArvore(Integer contadorArvore) {
		this.contadorArvore = contadorArvore;
	}

	public List<Produto> getProdutosUsuario() {
		return produtosUsuario;
	}

	public void setProdutosUsuario(List<Produto> produtosUsuario) {
		this.produtosUsuario = produtosUsuario;
	}

	public List<Produto> getMeusFavoritos() {
		return meusFavoritos;
	}

	public void setMeusFavoritos(List<Produto> meusFavoritos) {
		this.meusFavoritos = meusFavoritos;
	}

	public List<Produto> getMinhasCompras() {
		return minhasCompras;
	}

	public void setMinhasCompras(List<Produto> minhasCompras) {
		this.minhasCompras = minhasCompras;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

}