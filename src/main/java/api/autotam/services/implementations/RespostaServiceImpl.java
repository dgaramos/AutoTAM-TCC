package api.autotam.services.implementations;

import api.autotam.daos.interfaces.RespostaDAO;
import api.autotam.model.Resposta;
import api.autotam.services.interfaces.RespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service("respostaService")
@Transactional
public class RespostaServiceImpl extends AbstractService implements RespostaService  {


    @Autowired
    private RespostaDAO respostaDAO;

    @Override
    public void saveResposta(Resposta resposta) {
        respostaDAO.saveResposta(resposta);
    }

    @Override
    public void updateResposta(Resposta resposta) {
        respostaDAO.updateResposta(resposta);
    }

    @Override
    public void deleteResposta(int idResposta) {
        respostaDAO.deleteResposta(idResposta);
    }

    @Override
    public Resposta findById(int idResposta) {
        return respostaDAO.findById(idResposta);
    }

    @Override
    public List<Resposta> findAllRespostasFromQuestao(int idQuestao) {
        return null;
    }

    @Override
    public List<Resposta> findAllRespostasFromQuestionario(int idQuestionario) {
        return null;
    }

    @Override
    public List<Resposta> findAllRespostasFromUsuario(int idUsuario) {
        return null;
    }
}
