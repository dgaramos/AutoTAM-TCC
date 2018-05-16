package api.autotam.daos.interfaces;

import api.autotam.model.ResultadoOpcaoVariavel;

public interface ResultadoOpcaoVariavelDAO {


    void saveResultadoOpcaoVariavel(ResultadoOpcaoVariavel resultadoOpcaoVariavel);

    ResultadoOpcaoVariavel findById(int idResultadoOpcaoVariavel);

    void updateResultadoOpcaoVariavel(ResultadoOpcaoVariavel resultadoOpcaoVariavel);

    void deleteResultadoOpcaoVariavel(int idResultadoOpcaoVariavel);
}
