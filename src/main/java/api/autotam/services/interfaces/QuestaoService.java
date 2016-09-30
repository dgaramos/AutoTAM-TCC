package api.autotam.services.interfaces;

import api.autotam.model.Questao;
import api.autotam.model.VariavelTAM;

/**
 *  Interface responsável por encapsular as assinaturas dos métodos de regra de negócio referentes a classe Questão.
 *
 * @author Danilo
 */
public interface QuestaoService {

    void saveQuestao(Questao questao);

    Questao findById(int idQuestao);

    void updateQuestao(Questao questao);

    void deleteQuestao(int idQuestao);

    boolean isQuestaoExist(Questao questao);

}
