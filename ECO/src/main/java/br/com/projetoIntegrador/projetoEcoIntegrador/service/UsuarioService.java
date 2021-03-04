package br.com.projetoIntegrador.projetoEcoIntegrador.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projetoIntegrador.projetoEcoIntegrador.model.Usuario;
import br.com.projetoIntegrador.projetoEcoIntegrador.model.UsuarioLogin;
import br.com.projetoIntegrador.projetoEcoIntegrador.repository.UsuarioRepository;



@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public Usuario CadastrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenhaUsuario());
		usuario.setSenhaUsuario(senhaEncoder);

		return repository.save(usuario);
	}

	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByNomeUsuario(user.get().getNomeUsuario());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenhaUsuario(), usuario.get().getSenhaUsuario())) {

				String auth = user.get().getNomeUsuario() + ":" + user.get().getSenhaUsuario();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);				
				user.get().setNomeUsuario(usuario.get().getNomeUsuario());
				user.get().setSenhaUsuario(usuario.get().getSenhaUsuario());

				return user;
			}
		}
		return null;
	}
}