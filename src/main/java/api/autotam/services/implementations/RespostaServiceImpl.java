package api.autotam.services.implementations;

import api.autotam.model.Resposta;
import api.autotam.services.interfaces.RespostaService;

import java.util.List;

public class RespostaServiceImpl extends AbstractService implements RespostaService  {

    @Override
    public void saveResposta(Resposta questionario) {

    }

    @Override
    public void updateResposta(Resposta questionario) {

    }

    @Override
    public void deleteResposta(int idResposta) {

    }

    @Override
    public Resposta findById(int idResposta) {
        return null;
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
