package api.autotam.services.interfaces;

import api.autotam.model.OpcaoDeObjeto;
import api.autotam.model.Questao;
import api.autotam.model.ResultadoOpcaoQuestao;

import java.util.List;

public interface ResultadoOpcaoQuestaoService {

    void saveResultadoOpcaoQuestao(ResultadoOpcaoQuestao resultadoOpcaoQuestao);

    void updateResultadoOpcaoQuestao(ResultadoOpcaoQuestao resultadoOpcaoQuestao);

    void deleteResultadoOpcaoQuestao (int idResultadoOpcaoQuestao);

    ResultadoOpcaoQuestao findById(int idResultadoOpcaoQuestao);

    List<ResultadoOpcaoQuestao> findAllResultadosOpcaoQuestaoFromResultadoOpcaoVariavel (int idResultadoOpcaoVariavel);

    void calcularResultadoQuestao(OpcaoDeObjeto opcaoDeObjeto, Questao questao);
}
