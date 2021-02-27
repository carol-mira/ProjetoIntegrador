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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity

public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//talvez tirar 
	private Long idUsuario;
	
	@Size(min = 3 , max = 50) //controller tem que chamar pelo nome social 
	private String nomeSocial;
	
	@NotNull(message = "Preciso de um apelido. Como devo te chamar?")
	@Size(min = 3 , max = 50)
	private String nomeUsuario;

	@NotNull(message = "Preciso de um nome. Como devo te chamar?")
	@Size(min = 3 , max = 50)
	private String nomeCompletoUsuario;
	
	@NotNull(message = "Para entramos em contato infome o seu email.")
	@Size(min = 3 , max = 50)
	private String emailUsuario;
	
	@NotNull(message = "Para sua segurança, digite uma senha. ")//estruturar melhor para deixar sensitivo (Valid faz quantidade de caracteres e tal. CaseSensitive é no front.)
	@Size(min = 3 , max = 50)
	private String senhaUsuario;
	
	@OneToMany(mappedBy =  "usuario" , cascade = CascadeType.ALL)
	@JsonIgnoreProperties("usuario")

	private List<Produto> produtosUsuarios;

	public Usuario(Long idUsuario,String nomeSocial, String nomeUsuario, String nomeCompletoUsuario, String emailUsuario, String senhaUsuario,
			List<Produto> produtosUsuarios) {
		this.idUsuario = idUsuario;
		this.nomeSocial = nomeSocial;
		this.nomeUsuario = nomeUsuario;
		this.nomeCompletoUsuario = nomeCompletoUsuario;
		this.emailUsuario = emailUsuario;
		this.senhaUsuario = senhaUsuario;
		this.produtosUsuarios = produtosUsuarios;
	}

	public Usuario(Long idUsuario, String nomeUsuario, String emailUsuario, String senhaUsuario,String nomeCompletoUsuario) {
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.emailUsuario = emailUsuario;
		this.senhaUsuario = senhaUsuario;
		this.nomeCompletoUsuario= nomeCompletoUsuario;
	}
	

}
