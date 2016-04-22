package api.autotam.service;

import javax.transaction.Transactional;

import api.autotam.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.autotam.model.Usuario;

import java.util.List;


@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDAO dao;

    public void saveUsuario(Usuario usuario) {
        dao.saveUsuario(usuario);
    }

    public List<Usuario> findAllUsuarios() {
        return dao.findAllUsuarios();
    }

    public void deleteUsuarioByEmail(Usuario usuario) {
        dao.deleteUsuarioByEmail(usuario);
    }

    public Usuario findById(Integer id) {
        return dao.findById(id);
    }

    public Usuario findByEmail(String email) {
        return dao.findByEmail(email);
    }

    public void updateUsuario(Usuario usuario){
        dao.updateUsuario(usuario);
    }

    public boolean isUsuarioExist(Usuario usuario) {
        return findById(usuario.getIdUsuario())!=null;
    }

}