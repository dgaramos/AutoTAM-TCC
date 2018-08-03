package api.autotam.daos.interfaces;

import api.autotam.model.Resposta;
import java.util.List;

public interface RespostaDAO {

    void saveResposta(Resposta resposta);

    Resposta findById(int idResposta);

    void updateResposta(Resposta resposta);

    void deleteResposta(int idResposta);

    List<Resposta> findAllRespostasFromQuestao(int idQuestao);

    List<Resposta> findAllRespostasFromQuestionario(int idQuestionario);

    List<Resposta> findAllRespostasFromUsuario(int idUsuario);

    List<Resposta> findAllRespostasFromOpcaoDeObjeto(int idOpcaoDeObjeto);

    List<Resposta> findAllRespostasFromOpcaoDeObjetoAndQuestao(int idOpcaoDeObjeto, int idQuestao);

}
