package api.autotam.services.implementations;

import api.autotam.daos.interfaces.QuestaoDAO;
import api.autotam.model.Questao;
import api.autotam.services.interfaces.QuestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Classe de serviço responsável por encapsular as regras de negócio referentes as Questões da aplicação.
 *
 * @author Danilo
 */

@Service("questaoService")
@Transactional
public class QuestaoServiceImpl extends AbstractService implements QuestaoService {

    @Autowired
    private QuestaoDAO questaoDAO;

    /**
     * Método responsável pela operação de cadastro de uma Questão no banco de dados.
     *
     * @param questao
     */
    @Override
    public void saveQuestao(Questao questao) {
        if(usuarioLogadoIsAdministrador(questao.getVariavelTAM().getAnalise().getIdAnalise())){
            questaoDAO.saveQuestao(questao);
        }else{
            throw new SecurityException("Usuario não tem permissão de administrador para essa análise");
        }
    }

    /**
     * Método responsável pela operação de busca de uma determinada Questão por meio de seu id.
     *
     * @param idQuestao
     * @return
     */
    @Override
    public Questao findById(int idQuestao) {
        return questaoDAO.findById(idQuestao);
    }

    /**
     * Método responsável pela operação de atualização das informações de uma determinada Questão verificando se
     * o Usuário em sessão possui permissão de Administrador para realizar a operação.
     *
     * @param questao
     */
    @Override
    public void updateQuestao(Questao questao) {
        if(usuarioLogadoIsAdministrador(questao.getVariavelTAM().getAnalise().getIdAnalise())){
            questaoDAO.updateQuestao(questao);
        }else{
            throw new SecurityException("Usuario não tem permissão de administrador para essa análise");
        }
    }

    /**
     * Método responsável por apagar as informações de uma determinada Questão por meio de seu id verificando se o
     * Usuário em sessão possui permissão de Administrador para concluir a operação.
     *
     * @param idQuestao
     */
    @Override
    public void deleteQuestao(int idQuestao) {
        if(usuarioLogadoIsAdministrador(findById(idQuestao).getVariavelTAM().getAnalise().getIdAnalise())){
            questaoDAO.deleteQuestao(idQuestao);
        }else{
            throw new SecurityException("Usuario não tem permissão de administrador para essa análise");
        }
    }

    /**
     * Método responsável por verificar se uma determinada Questão existe no Banco de Dados.
     *
     * @param questao
     * @return
     */
    @Override
    public boolean isQuestaoExist(Questao questao) {
        return findById(questao.getIdQuestao())!=null;
    }

    /**
     * Método responsável por listar todas as questões de uma determinada Variável TAM por meio de seu id
     *
     * @param idVariavel
     * @return
     */
    @Override
    public List<Questao> findAllQuestoesFromVariavel(int idVariavel) {
        return questaoDAO.findAllQuestoesFromVariavel(idVariavel);
    }


}
