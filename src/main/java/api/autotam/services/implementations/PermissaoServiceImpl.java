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
    public Permissao findById(int idPermissao) {
        return permissaoDAO.findById(idPermissao);
    }

    @Override
    public List<Permissao> findAllPermissoesFromUsuario(int idUsuario){
        return permissaoDAO.findAllPermissoesFromUsuario(idUsuario);
    }

    @Override
    public List<Permissao> findAllPermissoesFromAnalise(int idAnalise) {
        return permissaoDAO.findAllPermissoesFromAnalise(idAnalise);
    }


    @Override
    public void savePermissao(Permissao permissao){
        if(!permissao.getUsuario().equals(getUsuarioLogado()) &&
                !permissaoDAO.usuarioHasPermissaoToAnalise(permissao.getAnalise().getIdAnalise(), permissao.getUsuario().getIdUsuario())){
                permissaoDAO.savePermissao(permissao);
        }else {
            System.out.println("Você tentou adicionar uma permissão ao usuário logado " +
                    "ou a um usuário que já possui permissão para essa análise");
        }

    }
    @Override
    public void updatePermissao(Permissao permissao) {
        permissaoDAO.updatePermissao(permissao);
    }

    @Override
    public void deletePermissao(int idPermissao){
        permissaoDAO.deletePermissao(idPermissao);
    }
}
