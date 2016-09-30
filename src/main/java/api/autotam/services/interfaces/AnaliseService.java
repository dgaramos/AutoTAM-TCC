package api.autotam.services.interfaces;

import api.autotam.model.Analise;
import api.autotam.model.VariavelTAM;

/**
 * Created by Danilo on 9/12/2016.
 */
public interface AnaliseService {

    void saveAnalise(Analise analise);

    Analise findById(int idAnalise);

    void updateAnalise(Analise analise);

    void deleteAnalise(int idAnalise);

    boolean isAnaliseExist(Analise analise);

    void addVariavelToAnalise(int idAnalise, VariavelTAM variavel);

}
