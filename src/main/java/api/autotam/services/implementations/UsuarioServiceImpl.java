package api.autotam.services.implementations;

import javax.transaction.Transactional;

import api.autotam.daos.interfaces.UsuarioDAO;
import api.autotam.services.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.autotam.model.Usuario;

import java.util.List;

/**
 * Essa classe implementa a interface UsuarioService, ela serve para encapsular as regras de negócio
 * em uma camada separada da busca de dados.
 *
 * Created by Danilo on 9/4/2016.
 */
@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl extends AbstractService implements UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public Usuario getUsuarioLogado() {
        return super.getUsuarioLogado();
    }

    public void saveUsuario(Usuario usuario) {
        usuarioDAO.saveUsuario(usuario);
    }

    public List<Usuario> findAllUsuarios() {
        return usuarioDAO.findAllUsuarios();
    }

    public void deleteUsuario(int idUsuario) {usuarioDAO.deleteUsuario(idUsuario);}

    public Usuario findById(Integer id) {
        return usuarioDAO.findById(id);
    }

    public Usuario findByEmail(String email) {
        return usuarioDAO.findByEmail(email);
    }

    public void updateUsuario(Usuario usuario){
        usuarioDAO.updateUsuario(usuario);
    }

    public boolean isUsuarioExist(Usuario usuario) {
        return findByEmail(usuario.getEmail())!=null;
    }


}