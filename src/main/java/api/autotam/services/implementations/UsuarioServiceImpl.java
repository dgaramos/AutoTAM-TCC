package api.autotam.services.implementations;

import javax.transaction.Transactional;

import api.autotam.daos.interfaces.UsuarioDAO;
import api.autotam.services.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.autotam.model.Usuario;

import java.util.List;

/**
 * Classe de serviço responsável por encapsular as regras de negócio referentes aos Usuários da aplicação.
 *
 * @author Danilo
 */

@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl extends AbstractService implements UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public void saveUsuario(Usuario usuario) {
        usuarioDAO.saveUsuario(usuario);
    }

    @Override
    public Usuario findById(Integer id) {
        return usuarioDAO.findById(id);
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioDAO.findByEmail(email);
    }

    @Override
    public Usuario getUsuarioLogado() {
        return super.getUsuarioLogado();
    }

    @Override
    public void updateUsuario(Usuario usuario){
        if(getUsuarioLogado().equals(usuario)){

            usuarioDAO.updateUsuario(usuario);

        }else{
            throw new SecurityException("Só o próprio usuário pode atualizar informações da sua conta");
        }
    }

    @Override
    public void deleteUsuario(int idUsuario) {
        if(getUsuarioLogado().getIdUsuario() == idUsuario){

            usuarioDAO.deleteUsuario(idUsuario);

        }else{
            throw new SecurityException("Só o próprio usuário pode excluir sua conta");
        }
    }

    @Override
    public boolean isUsuarioExist(Usuario usuario) {
        return findByEmail(usuario.getEmail())!=null;
    }

    @Override
    public List<Usuario> findAllUsuarios() {
        return usuarioDAO.findAllUsuarios();
    }






}