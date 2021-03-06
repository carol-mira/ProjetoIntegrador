package br.com.projetoIntegrador.projetoEcoIntegrador.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projetoIntegrador.projetoEcoIntegrador.model.Produto;
import br.com.projetoIntegrador.projetoEcoIntegrador.model.Usuario;
import br.com.projetoIntegrador.projetoEcoIntegrador.model.UsuarioLogin;
import br.com.projetoIntegrador.projetoEcoIntegrador.repository.ProdutoRepository;
import br.com.projetoIntegrador.projetoEcoIntegrador.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public Usuario CadastrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenhaUsuario());
		usuario.setSenhaUsuario(senhaEncoder);

		return usuarioRepository.save(usuario);
	}

	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		Optional<Usuario> usuario = usuarioRepository.findByEmailUsuario(user.get().getEmailUsuario());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenhaUsuario(), usuario.get().getSenhaUsuario())) {

				String auth = user.get().getEmailUsuario() + ":" + user.get().getSenhaUsuario();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);
				user.get().setCpf(usuario.get().getCpf());
				user.get().setNomeSocial(usuario.get().getNomeSocial());
				user.get().setNomeCompletoUsuario(usuario.get().getNomeCompletoUsuario());
				user.get().setNomeUsuario(usuario.get().getNomeUsuario());
				user.get().setEmailUsuario(usuario.get().getEmailUsuario());
				user.get().setSenhaUsuario(usuario.get().getSenhaUsuario());
				user.get().setContadorArvore(usuario.get().getContadorArvore());
				user.get().setMeusFavoritos(usuario.get().getMeusFavoritos());
				user.get().setMinhasCompras(usuario.get().getMinhasCompras());
				user.get().setProdutosUsuario(user.get().getProdutosUsuario());

				return user;
			}
		}
		return null;
	}

	public Produto cadastrarProduto(Produto novoProduto, String idUsuario) {
		Produto produtoExistente = produtoRepository.save(novoProduto);
		Optional<Usuario> usuarioExistente = usuarioRepository.findById(idUsuario);

		if (usuarioExistente.isPresent()) {
			produtoExistente.setUsuario(usuarioExistente.get());
			return produtoRepository.save(produtoExistente);
		}
		return null;
	}

	public Usuario deletarProduto(Long idProduto, String idUsuario) {
		Optional<Usuario> usuarioExistente = usuarioRepository.findById(idUsuario);
		Optional<Produto> produtoExistente = produtoRepository.findById(idProduto);

		if (usuarioExistente.isPresent() && produtoExistente.isPresent()) {
			produtoExistente.get().setUsuario(null);
			produtoRepository.save(produtoExistente.get());
			produtoRepository.deleteById(produtoExistente.get().getIdProduto());
			return usuarioRepository.findById(usuarioExistente.get().getCpf()).get();
		}
		return null;
	}

	public Usuario favoritar(String idUsuario, Long idProduto) {
		Optional<Usuario> usuarioExistente = usuarioRepository.findById(idUsuario);
		Optional<Produto> produtoExistente = produtoRepository.findById(idProduto);

		if (usuarioExistente.isPresent() && produtoExistente.isPresent()) {
			usuarioExistente.get().getMeusFavoritos().add(produtoExistente.get());
			return usuarioRepository.save(usuarioExistente.get());
		}
		return null;
	}

	public Usuario desfavoritar(String cpf, Long idProduto) {
		Optional<Usuario> usuarioExistente = usuarioRepository.findById(cpf);
		Optional<Produto> produtoExistente = produtoRepository.findById(idProduto);

		if (usuarioExistente.isPresent() && produtoExistente.isPresent()) {
			usuarioExistente.get().getMeusFavoritos().remove(produtoExistente.get());
			return usuarioRepository.save(usuarioExistente.get());
		}
		return null;
	}

	public Usuario comprarProduto(String idUsuario, Long idProduto) {
		Optional<Usuario> usuarioExistente = usuarioRepository.findById(idUsuario);
		Optional<Produto> produtoExistente = produtoRepository.findById(idProduto);

		if (usuarioExistente.isPresent() && produtoExistente.isPresent()) {
			usuarioExistente.get().getMinhasCompras().add(produtoExistente.get());
			usuarioExistente.get().setContadorArvore(usuarioExistente.get().getContadorArvore() + 1);
			return usuarioRepository.save(usuarioExistente.get());
		}
		return null;
	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmailUsuario(usuario.getEmailUsuario());
		if (usuarioExistente.isPresent()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaEncoder = encoder.encode(usuario.getSenhaUsuario());
			usuarioExistente.get().setSenhaUsuario(senhaEncoder);
			return Optional.of(usuarioRepository.save(usuarioExistente.get()));
		}
		return Optional.empty();
	}

}