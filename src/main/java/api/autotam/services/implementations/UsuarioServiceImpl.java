package api.autotam.services.implementations;

import javax.transaction.Transactional;

import api.autotam.daos.interfaces.UsuarioDAO;
import api.autotam.services.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Método responsável pela operação de cadastro de um Usuário no banco de dados.
     *
     * @param usuario
     */
    @Override
    public void saveUsuario(Usuario usuario) {

        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(usuario.getEmail());
        novoUsuario.setNome(usuario.getNome());
        novoUsuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        usuarioDAO.saveUsuario(novoUsuario);
    }

    /**
     * Método responsável pela operação de busca de um determinado Usuário por meio de seu id.
     *
     * @param idUsuario
     * @return
     */
    @Override
    public Usuario findById(Integer idUsuario) {
        return usuarioDAO.findById(idUsuario);
    }

    /**
     * Método responsável pela operação de busca de um determinado Usuário por meio de seu email.
     *
     * @param email
     * @return
     */
    @Override
    public Usuario findByEmail(String email) {
        return usuarioDAO.findByEmail(email);
    }

    /**
     * Método responsável por retornar o Usuário em sessão.
     *
     * @return
     */
    @Override
    public Usuario getUsuarioLogado() {
        return super.getUsuarioLogado();
    }

    /**
     * Método responsável por atualizar um determinado Usuário verificando se o Usuário a ser atualizado
     * é o Usuário em sessão
     *
     * @param usuario
     */
    @Override
    public void updateUsuario(Usuario usuario){

        Usuario novosDados = usuarioDAO.findById(usuario.getIdUsuario());
        novosDados.setEmail(usuario.getEmail());
        novosDados.setNome(usuario.getNome());
        novosDados.setSenha(passwordEncoder.encode(usuario.getSenha()));

        usuarioDAO.updateUsuario(novosDados);
    }

    /**
     * Método responsável por apagar as informações de um determinado Usuário verificando se o Usuário
     * em sessão é o Usuário que está sendo apagado.
     *
     * @param idUsuario
     */
    @Override
    public void deleteUsuario(int idUsuario) {
            usuarioDAO.deleteUsuario(idUsuario);
    }

    /**
     * Método responsável por verificar se um determinado Usuário existe no Banco de Dados.
     *
     * @param usuario
     * @return
     */
    @Override
    public boolean isUsuarioExist(Usuario usuario) {
        return findByEmail(usuario.getEmail())!=null;
    }

    /**
     * Método responsável por listar todos os Usuários cadastrados no banco de dados.
     *
     * @return
     */
    @Override
    public List<Usuario> findAllUsuarios() {
        return usuarioDAO.findAllUsuarios();
    }






}