package api.autotam.services.interfaces;

import api.autotam.model.OpcaoDeObjeto;
import api.autotam.model.ResultadoOpcaoVariavel;
import api.autotam.model.VariavelTAM;

import java.util.List;

public interface ResultadoOpcaoVariavelService {

    void saveResultadoOpcaoVariavel(ResultadoOpcaoVariavel resultadoOpcaoVariavel);

    void updateResultadoOpcaoVariavel(ResultadoOpcaoVariavel resultadoOpcaoVariavel);

    void deleteResultadoOpcaoVariavel (int idResultadoOpcaoVariavel);

    ResultadoOpcaoVariavel findById(int idResultadoOpcaoVariavel);

    void calcularResultadoVariavel(OpcaoDeObjeto opcaoDeObjeto, VariavelTAM variavel);

    List<ResultadoOpcaoVariavel> findFromOpcao(int idOpcaoDeObjeto);
}
