package api.autotam.daos.interfaces;

import api.autotam.model.ResultadoOpcaoVariavel;

import java.util.List;

public interface ResultadoOpcaoVariavelDAO {


    void saveResultadoOpcaoVariavel(ResultadoOpcaoVariavel resultadoOpcaoVariavel);

    ResultadoOpcaoVariavel findById(int idResultadoOpcaoVariavel);

    void updateResultadoOpcaoVariavel(ResultadoOpcaoVariavel resultadoOpcaoVariavel);

    void deleteResultadoOpcaoVariavel(int idResultadoOpcaoVariavel);

    ResultadoOpcaoVariavel findFromOpcaoVariavel (int idOpcaoDeObjeto, int idVariavel);

    List<ResultadoOpcaoVariavel> findFromOpcao(int idOpcaoDeObjeto);

}
