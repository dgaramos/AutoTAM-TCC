package api.autotam.daos.interfaces;

import api.autotam.model.Questao;

import java.util.List;

/**
 * Created by Danilo on 9/24/2016.
 */
public interface QuestaoDAO {

    void saveQuestao(Questao questao);

    void updateQuestao(Questao questao);

    List<Questao> findAllQuestoesFromVariavel(int idVariavel);

    Questao findById(int idQuestao);

    void deleteQuestao(int idQuestao);



}