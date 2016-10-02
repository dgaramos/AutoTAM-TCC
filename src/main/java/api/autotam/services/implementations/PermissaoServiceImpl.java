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
 * Classe de serviço responsável por encapsular as regras de negócio referentes as Permissões da aplicação.
 *
 * @author Danilo
 */

@Service("permissaoService")
@Transactional
public class PermissaoServiceImpl extends AbstractService implements PermissaoService {

    @Autowired
    private PermissaoDAO permissaoDAO;

    /**
     * Método responsável pela operação de cadastro de uma Permissão de acesso a uma determinada Análise para um
     * determinado Usuário (que não está em sessão), verificando se o Usuário em sessão possui permissão de
     * Administrador para realizar a operação.
     *
     * @param permissao
     */
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

    /**
     * Método responsável pela operação de busca de uma determinada Permissão por meio de seu id.
     *
     * @param idPermissao
     * @return
     */
    @Override
    public Permissao findById(int idPermissao) {
        return permissaoDAO.findById(idPermissao);
    }

    /**
     * Método responsável pela operação de atualização das informações de uma determinada Permissão verificando se
     * o Usuário em sessão possui permissão de Administrador para realizar a operação.
     *
     * @param permissao
     */
    @Override
    public void updatePermissao(Permissao permissao) {
        if (usuarioLogadoIsAdministrador(permissao.getAnalise().getIdAnalise())){
            permissaoDAO.updatePermissao(permissao);
        }else{
            throw new SecurityException("Usuario não tem permissão de administrador para essa análise");
        }
    }

    /**
     * Método responsável por apagar as informações de uma determinada Permissão verificando se o Usuário
     * em sessão possui permissão de Administrador para concluir a operação.
     *
     * @param idPermissao
     */
    @Override
    public void deletePermissao(int idPermissao){
        if (usuarioLogadoIsAdministrador(findById(idPermissao).getAnalise().getIdAnalise())){
            permissaoDAO.deletePermissao(idPermissao);
        }else{
            throw new SecurityException("Usuario não tem permissão de administrador para essa análise");
        }
    }

    /**
     * Método responsável por verificar se o Usuário já possui uma Permissão para uma determinada Análise com
     * o intuito de evitar que um Usuário tenha mais de uma Permissão para uma Análise
     *
     * @param permissao
     */
    @Override
    public boolean isUsuarioHavePermissaoToAnalise(Permissao permissao){
        return permissaoDAO.usuarioHasPermissaoToAnalise(permissao.getAnalise().getIdAnalise(),
                permissao.getUsuario().getIdUsuario());
    }

    /**
     * Método responsável por listar todas as Permissões do Usuário em sessão.
     *
     * @return
     */
    @Override
    public List<Permissao> findAllPermissoesFromUsuarioLogado(){
        return permissaoDAO.findAllPermissoesFromUsuario(getUsuarioLogado().getIdUsuario());
    }

    /**
     * Método responsável por listar todas as Permissões de uma determinada Análise no banco de dados por meio
     * de seu id verificando se o Usuário em sessão tem permissão para visualizar a Análise
     *
     * @param idAnalise
     * @return
     */
    @Override
    public List<Permissao> findAllPermissoesFromAnalise(int idAnalise) {
        if (usuarioLogadoIsAdministrador(idAnalise)){
            return permissaoDAO.findAllPermissoesFromAnalise(idAnalise);
        }else{
            throw new SecurityException("Usuario não tem permissão de administrador para essa análise");
        }

    }


}
