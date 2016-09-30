package api.autotam.services.implementations;

import api.autotam.daos.interfaces.PermissaoDAO;
import api.autotam.model.Permissao;
import api.autotam.services.interfaces.PermissaoService;
import org.hibernate.service.spi.ServiceException;
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
    public void savePermissao(Permissao permissao){
        if (usuarioLogadoIsAdministrador(permissao.getAnalise().getIdAnalise())){
            if(!permissao.getUsuario().equals(getUsuarioLogado()) && !isUsuarioHavePermissaoToAnalise(permissao)){
                    permissaoDAO.savePermissao(permissao);
            }else {
                throw new ServiceException("Você tentou adicionar uma permissão ao usuário logado " +
                        "ou a um usuário que já possui permissão para essa análise");
            }
        }else{
            throw new SecurityException("Usuario não tem permissão de administrador para essa análise");
        }

    }

    @Override
    public Permissao findById(int idPermissao) {
        return permissaoDAO.findById(idPermissao);
    }

    @Override
    public void updatePermissao(Permissao permissao) {
        if (usuarioLogadoIsAdministrador(permissao.getAnalise().getIdAnalise())){
            permissaoDAO.updatePermissao(permissao);
        }else{
            throw new SecurityException("Usuario não tem permissão de administrador para essa análise");
        }
    }

    @Override
    public void deletePermissao(int idPermissao){
        if (usuarioLogadoIsAdministrador(findById(idPermissao).getAnalise().getIdAnalise())){
            permissaoDAO.deletePermissao(idPermissao);
        }else{
            throw new SecurityException("Usuario não tem permissão de administrador para essa análise");
        }
    }

    @Override
    public boolean isUsuarioHavePermissaoToAnalise(Permissao permissao){
        return permissaoDAO.usuarioHasPermissaoToAnalise(permissao.getAnalise().getIdAnalise(),
                permissao.getUsuario().getIdUsuario());
    }

    @Override
    public List<Permissao> findAllPermissoesFromUsuarioLogado(){
        return permissaoDAO.findAllPermissoesFromUsuario(getUsuarioLogado().getIdUsuario());
    }

    @Override
    public List<Permissao> findAllPermissoesFromAnalise(int idAnalise) {
        return permissaoDAO.findAllPermissoesFromAnalise(idAnalise);
    }


}
