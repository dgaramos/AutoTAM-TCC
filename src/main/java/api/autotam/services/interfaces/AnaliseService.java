package api.autotam.services.interfaces;

import api.autotam.model.Analise;
import api.autotam.model.VariavelTAM;

/**
 * Created by Danilo on 9/12/2016.
 */
public interface AnaliseService {

    void saveAnalise(Analise analise);

    void addVariavelToAnalise(int idAnalise, VariavelTAM variavel);

    Analise findById(int idAnalise);

    void deleteAnalise(int idAnalise);

    void updateAnalise(Analise analise);

    boolean isAnaliseExist(Analise analise);

}
