package api.autotam.services.interfaces;

import api.autotam.model.Permissao;

import java.util.List;

/**
 * Created by Danilo on 9/29/2016.
 */
public interface PermissaoService {

    Permissao findById(int idPermissao);

    List<Permissao> findAllPermissoesFromUsuarioLogado();

    List<Permissao> findAllPermissoesFromAnalise(int idAnalise);

    void savePermissao(Permissao permissao);

    void updatePermissao(Permissao permissao);

    boolean isUsuarioHavePermissaoToAnalise(Permissao permissao);

    void deletePermissao (int idPermissao);



}
