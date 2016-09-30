package api.autotam.daos.interfaces;

import api.autotam.model.Questao;

import java.util.List;

/**
 * Interface responsável por encapsular as assinaturas dos métodos das operações referentes aos registros no banco de
 * dados da classe Questão.
 *
 * @author Danilo
 */

public interface QuestaoDAO {

    void saveQuestao(Questao questao);

    Questao findById(int idQuestao);

    void updateQuestao(Questao questao);

    void deleteQuestao(int idQuestao);

    List<Questao> findAllQuestoesFromVariavel(int idVariavel);

}