package br.com.projetoIntegrador.projetoEcoIntegrador.model;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Usuario {

	@Id
	@NotNull
	@CPF
	private String cpf;

	@Size(min = 3, max = 50)
	private String nomeSocial;
	
	@NotNull
	private String telefone;

	@NotNull(message = "Preciso de um nome. Como devo te chamar?")
	@Size(min = 3, max = 50)
	private String nomeCompletoUsuario;


	@Size(min = 4, max = 20, message = "Insira um nome para te encontrarem")
	private String nomeUsuario;

	@NotNull(message = "Para entramos em contato infome o seu email.")
	@Size(min = 3, max = 50)
	private String emailUsuario;

	@NotNull(message = "Para sua segurança, digite uma senha. ") // estruturar melhor para deixar sensitivo
	private String senhaUsuario;

	private Integer contadorArvore = 0;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("usuario")
	private List<Produto> produtosUsuario;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "favoritos", joinColumns = @JoinColumn(name = "consumidor_id"), inverseJoinColumns = @JoinColumn(name = "produto_id"))
	@JsonIgnoreProperties("usuario")
	private List<Produto> meusFavoritos = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "compras", joinColumns = @JoinColumn(name = "consumidor_id"), inverseJoinColumns = @JoinColumn(name = "produto_id"))
	@JsonIgnoreProperties("usuario")
	private List<Produto> minhasCompras = new ArrayList<>();

	public Usuario() {
	}
	
	public Usuario(@CPF String cpf,  String nomeSocial, String telefone, String nomeCompletoUsuario, String nomeUsuario,String emailUsuario,
		 String senhaUsuario) {
	
		this.cpf = cpf;
		this.nomeSocial = nomeSocial;
		this.telefone = telefone;
		this.nomeCompletoUsuario = nomeCompletoUsuario;
		this.nomeUsuario = nomeUsuario;
		this.emailUsuario = emailUsuario;
		this.senhaUsuario = senhaUsuario;
	}

	public Usuario(Long idUsuario, @CPF String cpf, String nomeSocial, String nomeUsuario, String nomeCompletoUsuario,
			String emailUsuario, String senhaUsuario, String telefone) throws ParseException {
		this.cpf = cpf;
		this.nomeUsuario = nomeUsuario;
		this.nomeSocial = nomeSocial;
		this.nomeCompletoUsuario = nomeCompletoUsuario;
		this.emailUsuario = emailUsuario;
		this.senhaUsuario = senhaUsuario;
		this.telefone = telefone;
	}

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

	public Integer getContadorArvore() {
		return contadorArvore;
	}

	public void setContadorArvore(Integer contadorArvore) {
		this.contadorArvore = contadorArvore;
	}

	public List<Produto> getMinhasCompras() {
		return minhasCompras;
	}

	public void setMinhasCompras(List<Produto> minhasCompras) {
		this.minhasCompras = minhasCompras;
	}

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
  
	
	
 }

