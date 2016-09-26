package api.autotam.dao;

import api.autotam.model.Permissao;
import api.autotam.model.Usuario;

import java.util.List;

/**
 * Created by Danilo on 9/11/2016.
 */
public interface PermissaoDAO {

    void savePermissao(Permissao permissao);

    List<Permissao> findAllPermissoesByUsuario(int idUsuario);

    void deletePermissao(int idPermissao);

    Permissao findById(int idPermissao);

    void updatePermissao(Permissao permissao);
}
