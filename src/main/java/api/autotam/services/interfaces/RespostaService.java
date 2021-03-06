package api.autotam.services.interfaces;

import api.autotam.model.Resposta;

import java.util.List;

public interface RespostaService {

    void saveResposta(Resposta resposta);

    void updateResposta(Resposta resposta);

    void deleteResposta (int idResposta);

    Resposta findById(int idResposta);

    List<Resposta> findAllRespostasFromQuestao(int idQuestao);

    List<Resposta> findAllRespostasFromQuestionario(int idQuestionario);

    List<Resposta> findAllRespostasFromUsuario(int idUsuario);
}
