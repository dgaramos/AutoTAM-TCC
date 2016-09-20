package api.autotam.service;

import javax.transaction.Transactional;

import api.autotam.dao.UsuarioDAO;
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
    private UsuarioDAO usuarioDao;

    @Override
    public Usuario getUsuarioLogado() {
        return super.getUsuarioLogado();
    }

    public void saveUsuario(Usuario usuario) {
        usuarioDao.saveUsuario(usuario);
    }

    public List<Usuario> findAllUsuarios() {
        return usuarioDao.findAllUsuarios();
    }

    public void deleteUsuario(Usuario usuario) {
        usuarioDao.deleteUsuario(usuario);
    }

    public Usuario findById(Integer id) {
        return usuarioDao.findById(id);
    }

    public Usuario findByEmail(String email) {
        return usuarioDao.findByEmail(email);
    }

    public void updateUsuario(Usuario usuario){
        usuarioDao.updateUsuario(usuario);
    }

    public boolean isUsuarioExist(Usuario usuario) {
        return findByEmail(usuario.getEmail())!=null;
    }


}