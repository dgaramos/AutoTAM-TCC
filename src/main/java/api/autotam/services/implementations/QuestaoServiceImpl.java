package api.autotam.services.implementations;

import api.autotam.daos.interfaces.QuestaoDAO;
import api.autotam.model.Questao;
import api.autotam.services.interfaces.QuestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Danilo on 9/30/2016.
 */

@Service("questaoService")
@Transactional
public class QuestaoServiceImpl extends AbstractService implements QuestaoService {

    @Autowired
    private QuestaoDAO questaoDAO;

    @Override
    public void saveQuestao(Questao questao) {
        if(usuarioLogadoIsAdministrador(questao.getVariavelTAM().getAnalise().getIdAnalise())){
            questaoDAO.saveQuestao(questao);
        }else{
            throw new SecurityException("Usuario não tem permissão de administrador para essa análise");
        }
    }

    @Override
    public Questao findById(int idQuestao) {
        return questaoDAO.findById(idQuestao);
    }

    @Override
    public void updateQuestao(Questao questao) {
        if(usuarioLogadoIsAdministrador(questao.getVariavelTAM().getAnalise().getIdAnalise())){
            questaoDAO.updateQuestao(questao);
        }else{
            throw new SecurityException("Usuario não tem permissão de administrador para essa análise");
        }
    }

    @Override
    public void deleteQuestao(int idQuestao) {
        if(usuarioLogadoIsAdministrador(findById(idQuestao).getVariavelTAM().getAnalise().getIdAnalise())){
            questaoDAO.deleteQuestao(idQuestao);
        }else{
            throw new SecurityException("Usuario não tem permissão de administrador para essa análise");
        }
    }

    @Override
    public boolean isQuestaoExist(Questao questao) {
        return findById(questao.getIdQuestao())!=null;
    }


}
