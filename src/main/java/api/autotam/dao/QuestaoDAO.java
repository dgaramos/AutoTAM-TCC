package api.autotam.dao;

import api.autotam.model.Questao;

import java.util.List;

/**
 * Created by Danilo on 9/24/2016.
 */
public interface QuestaoDAO {

    void saveQuestao(Questao questao);

    List<Questao> findAllQuestoesFromVariavel(int idVariavel);

    void deleteQuestao(Questao questao);

    Questao findById(Integer id);

    void updateQuestao(Questao questao);

}