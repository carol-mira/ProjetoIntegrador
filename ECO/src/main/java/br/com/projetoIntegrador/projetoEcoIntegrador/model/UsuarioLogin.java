package br.com.projetoIntegrador.projetoEcoIntegrador.model;

public class UsuarioLogin {

	private String cpf;
	private String nomeSocial;
	private String nomeUsuario;
	private String nomeCompletoUsuario;
	private String emailUsuario;
	private String senhaUsuario;
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
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	
}