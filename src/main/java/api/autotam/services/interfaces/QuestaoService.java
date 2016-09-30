package api.autotam.services.interfaces;

import api.autotam.model.Questao;
import api.autotam.model.VariavelTAM;

/**
 * Created by Danilo on 9/30/2016.
 */
public interface QuestaoService {

    void saveQuestao(Questao questao);

    Questao findById(int idQuestao);

    void updateQuestao(Questao questao);

    void deleteQuestao(int idQuestao);

    boolean isQuestaoExist(Questao questao);

}
