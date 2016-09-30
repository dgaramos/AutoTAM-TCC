package api.autotam.services.interfaces;

import api.autotam.model.Permissao;

import java.util.List;

/**
 * Interface responsável por encapsular as assinaturas dos métodos de regra de negócio referentes a classe Permissão.
 *
 * @author Danilo
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
