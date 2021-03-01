package br.com.projetoIntegrador.projetoEcoIntegrador.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity

public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;

	@CPF
	private String cpf;

	@Size(min = 3, max = 50) // controller tem que chamar pelo nome social
	private String nomeSocial;

	@NotNull(message = "Preciso de um apelido. Como devo te chamar?")
	@Size(min = 3, max = 50)
	private String nomeUsuario;

	@NotNull(message = "Preciso de um nome. Como devo te chamar?")
	@Size(min = 3, max = 50)
	private String nomeCompletoUsuario;

	@NotNull(message = "Para entramos em contato infome o seu email.")
	@Size(min = 3, max = 50)
	private String emailUsuario;

	@NotNull(message = "Para sua segurança, digite uma senha. ") // estruturar melhor para deixar sensitivo
	@Size(min = 3, max = 50)
	private String senhaUsuario;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("usuario")
	private List<Produto> produtosUsuario;

	public Usuario() {

	}

	public Usuario(Long idUsuario, @CPF String cpf, String nomeSocial, String nomeUsuario, String nomeCompletoUsuario,
			String emailUsuario, String senhaUsuario) {
		this.idUsuario = idUsuario;
		this.cpf = cpf;
		this.nomeSocial = nomeSocial;
		this.nomeUsuario = nomeUsuario;
		this.nomeCompletoUsuario = nomeCompletoUsuario;
		this.emailUsuario = emailUsuario;
		this.senhaUsuario = senhaUsuario;
	}

	public Usuario(Long idUsuario, @CPF String cpf, String nomeUsuario, String nomeCompletoUsuario, String emailUsuario,
			String senhaUsuario) {
		this.idUsuario = idUsuario;
		this.cpf = cpf;
		this.nomeUsuario = nomeUsuario;
		this.nomeCompletoUsuario = nomeCompletoUsuario;
		this.emailUsuario = emailUsuario;
		this.senhaUsuario = senhaUsuario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
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

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeCompletoUsuario() {
		return nomeCompletoUsuario;
	}

	public void setNomeCompletoUsuario(String nomeCompletoUsuario) {
		this.nomeCompletoUsuario = nomeCompletoUsuario;
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

}
