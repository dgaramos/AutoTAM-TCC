package api.autotam.services.interfaces;

import api.autotam.model.Analise;
import api.autotam.model.VariavelTAM;

/**
 * Interface responsável por encapsular as assinaturas dos métodos de regra de negócio referentes a classe Análise.
 *
 * @author Danilo
 */
public interface AnaliseService {

    void saveAnalise(Analise analise);

    Analise findById(int idAnalise);

    void updateAnalise(Analise analise);

    void deleteAnalise(int idAnalise);

    boolean isAnaliseExist(Analise analise);

    void addVariavelToAnalise(int idAnalise, VariavelTAM variavel);

}
