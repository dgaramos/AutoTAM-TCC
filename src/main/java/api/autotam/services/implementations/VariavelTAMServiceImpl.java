package api.autotam.services.implementations;

import api.autotam.daos.interfaces.VariavelTAMDAO;
import api.autotam.model.Questao;
import api.autotam.model.VariavelTAM;
import api.autotam.services.interfaces.VariavelTAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Classe de serviço responsável por encapsular as regras de negócio referentes as Variáveis TAM da aplicação.
 *
 * @author Danilo
 */

@Service("variavelTAMService")
@Transactional
public class VariavelTAMServiceImpl extends AbstractService implements VariavelTAMService {

    @Autowired
    private VariavelTAMDAO variavelTAMDAO;


    /**
     * Método responsável pela operação de cadastro de uma Variável TAM no banco de dados.
     *
     * @param variavel
     */
    @Override
    public void saveVariavel(VariavelTAM variavel) {
        if(usuarioLogadoIsAdministrador(variavel.getAnalise().getIdAnalise())){
            variavelTAMDAO.saveVariavel(variavel);
        }else{
            throw new SecurityException("O usuário não é administrador da análise");
        }
    }

    /**
     * Método responsável pela operação de busca de uma determinada Variavel TAM por meio de seu id.
     *
     * @param idVariavel
     * @return
     */
    @Override
    public VariavelTAM findById(int idVariavel) {return variavelTAMDAO.findById(idVariavel);}

    /**
     * Método responsável pela operação de atualização das informações de uma determinada Variavel TAM verificando se o
     * Usuário em sessão possui permissão de Administrador para concluir a operação.
     *
     * @param variavel
     */
    @Override
    public void updateVariavel(VariavelTAM variavel) {
        if(usuarioLogadoIsAdministrador(variavel.getAnalise().getIdAnalise())){

            for (int i = 0; i < variavel.getQuestoes().size(); i++) {
                Questao questao = variavel.getQuestoes().get(i);
                questao.setNumero(i + 1);
                variavel.getQuestoes().set(i, questao);
            }

            variavelTAMDAO.updateVariavel(variavel);
        }else{
            throw new SecurityException("O usuário não é administrador da análise");
        }
    }

    /**
     * Método responsável por apagar as informações de uma determinada Variável TAM verificando se o Usuário
     * em sessão possui permissão de Administrador para concluir a operação.
     *
     * @param idVariavel
     */
    @Override
    public void deleteVariavel(int idVariavel) {
        if(usuarioLogadoIsAdministrador(findById(idVariavel).getAnalise().getIdAnalise())){
            variavelTAMDAO.deleteVariavel(idVariavel);
        }else{
            throw new SecurityException("O usuário não é administrador da análise");
        }
    }

    /**
     * Método responsável por verificar se uma determinada Variável TAM existe no Banco de Dados.
     *
     * @param variavel
     * @return
     */
    @Override
    public boolean isVariavelExist(VariavelTAM variavel) {return findById(variavel.getIdVariavel()) != null;}

    /**
     * Método responsável por buscar todas as Variáveis TAM de uma determinada Análise no banco de dados.
     *
     * @param idAnalise
     * @return
     */
    @Override
    public List<VariavelTAM> findAllVariaveisFromAnalise(int idAnalise) {
        List<VariavelTAM> variaveis = variavelTAMDAO.findAllVariaveisFromAnalise(idAnalise);
        return variaveis;
    }

    /**
     * Método responsável pela operação de cadastro de uma Questão em uma determinada VariavelTAM, verificando se o
     * Usuário em sessão possui permissão de Administrador para concluir a operação.
     *
     * @param idVariavel
     * @param questao
     */
    @Override
    public void addQuestaoToVariavel(int idVariavel, Questao questao){
        if (usuarioLogadoIsAdministrador(findById(idVariavel).getAnalise().getIdAnalise())){
            VariavelTAM variavelTAM = findById(idVariavel);
            questao.setVariavelTAM(variavelTAM);
            List<Questao> questoes = variavelTAM.getQuestoes();
            questoes.add(questao);
            variavelTAM.setQuestoes(questoes);
            updateVariavel(variavelTAM);
        }else{
            throw new SecurityException("Usuario não tem permissão de administrador para essa análise");
        }

    }
}
