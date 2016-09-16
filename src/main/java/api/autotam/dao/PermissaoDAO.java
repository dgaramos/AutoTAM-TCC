package api.autotam.dao;

import api.autotam.model.Permissao;
import api.autotam.model.Usuario;

import java.util.List;

/**
 * Created by Danilo on 9/11/2016.
 */
public interface PermissaoDAO {

    void savePermissao(Permissao permissao);

    List<Permissao> findAllPermissoes();

    List<Permissao> findAllPermissoesByUsuario(int idUsuario);

    void deletePermissao(Permissao permissao);

    Permissao findById(Integer id);

    void updatePermissao(Permissao permissao);
}
