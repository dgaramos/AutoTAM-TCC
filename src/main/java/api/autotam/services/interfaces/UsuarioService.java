package api.autotam.services.interfaces;

import api.autotam.model.Usuario;

import java.util.List;

/**
 * Interface responsável por encapsular as assinaturas dos métodos de regra de negócio referentes a classe Usuario.
 *
 * @author Danilo
 */
public interface UsuarioService {

    void saveUsuario(Usuario usuario);

    Usuario findById(Integer id);

    Usuario findByEmail(String email);

    Usuario getUsuarioLogado();

    void updateUsuario(Usuario usuario);

    void deleteUsuario(int idUsuario);

    boolean isUsuarioExist(Usuario usuario);

    List<Usuario> findAllUsuarios();

}


