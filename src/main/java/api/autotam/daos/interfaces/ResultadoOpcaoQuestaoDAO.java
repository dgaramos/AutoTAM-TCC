package api.autotam.daos.interfaces;

import api.autotam.model.ResultadoOpcaoQuestao;

import java.util.List;

public interface ResultadoOpcaoQuestaoDAO {

    void saveResultadoOpcaoQuestao(ResultadoOpcaoQuestao resultadoOpcaoQuestao);

    ResultadoOpcaoQuestao findById(int idResultadoOpcaoQuestao);

    void updateResultadoOpcaoQuestao(ResultadoOpcaoQuestao resultadoOpcaoQuestao);

    void deleteResultadoOpcaoQuestao(int idResultadoOpcaoQuestao);

    List<ResultadoOpcaoQuestao> findAllResultadosOpcaoQuestaoFromResultadoOpcaoVariavel (int idResultadoOpcaoVariavel);

    ResultadoOpcaoQuestao findFromOpcaoQuestao (int idOpcaoDeObjeto, int idQuestao);
}
