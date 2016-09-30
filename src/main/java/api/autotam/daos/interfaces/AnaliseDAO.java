package api.autotam.daos.interfaces;

import api.autotam.model.Analise;

/**
 * Interface responsável por encapsular as assinaturas dos métodos das operações referentes aos registros no banco de
 * dados da classe Análise.
 *
 * @author Danilo
 */

public interface AnaliseDAO {

    void saveAnalise(Analise analise);

    Analise findById(int idAnalise);

    Analise findByObjetoDeAnalise(String objetoDeAnalise);

    void updateAnalise(Analise analise);

    void deleteAnalise(int idAnalise);

}
