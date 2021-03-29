package br.com.projetoIntegrador.projetoEcoIntegrador.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projetoIntegrador.projetoEcoIntegrador.model.Usuario;
import br.com.projetoIntegrador.projetoEcoIntegrador.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String emailUsuario) throws UsernameNotFoundException {

		Optional<Usuario> user = userRepository.findByEmailUsuario(emailUsuario);
		user.orElseThrow(() -> new UsernameNotFoundException(emailUsuario + " not found."));

		return user.map(UserDetailsImpl::new).get();
	}
}