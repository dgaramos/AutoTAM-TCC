package api.autotam.services.implementations;

import api.autotam.daos.interfaces.PermissaoDAO;
import api.autotam.model.Permissao;
import api.autotam.services.interfaces.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Danilo on 9/29/2016.
 */

@Service("permissaoService")
@Transactional
public class PermissaoServiceImpl extends AbstractService implements PermissaoService {

    @Autowired
    private PermissaoDAO permissaoDAO;


    @Override
    public List<Permissao> findAllPermissoesFromUsuario(int idUsuario){
        List<Permissao> permissoes = permissaoDAO.findAllPermissoesFromUsuario(idUsuario);
        return permissoes;
    }

    @Override
    public List<Permissao> findAllPermissoesFromAnalise(int idAnalise) {
        List<Permissao> permissoes = permissaoDAO.findAllPermissoesFromAnalise(idAnalise);
        return permissoes;
    }


    @Override
    public void savePermissao(Permissao permissao){
        if(!permissao.getUsuario().equals(getUsuarioLogado())){

            permissaoDAO.savePermissao(permissao);
        }else {
            System.out.println("Você tentou adicionar uma permissão ao usuário logado(que já possui permissões)");
        }

    }
    @Override
    public void updatePermissao(Permissao permissao) {

    }

    @Override
    public void deletePermissao(int idPermissao){
        permissaoDAO.deletePermissao(idPermissao);
    }
}
