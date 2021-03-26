package br.com.projetoIntegrador.projetoEcoIntegrador.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetoIntegrador.projetoEcoIntegrador.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

    public List<Usuario> findAllByNomeUsuarioContainingIgnoreCase(String nomeUsuario);// Esse é necessário?
    public List<Usuario> findAllByNomeSocialContainingIgnoreCase(String nomeSocial);
    public List<Usuario> findAllByNomeCompletoUsuarioContainingIgnoreCase(String nomeCompletoUsuario);
    public List<Usuario> findAllByEmailUsuarioContainingIgnoreCase(String emailUsuario);
    public List<Usuario> findAllByProdutosUsuarioContainingIgnoreCase(String produtosUsuario);//falar com Boaz
    public Optional<Usuario> findByNomeUsuario(String NomeUsuario);
  
    
}
