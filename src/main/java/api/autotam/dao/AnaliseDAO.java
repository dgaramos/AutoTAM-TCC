package api.autotam.dao;

import api.autotam.model.Analise;

import java.util.List;

/**
 * Created by Danilo on 9/11/2016.
 */
public interface AnaliseDAO {

    void saveAnalise(Analise analise);


    void deleteAnalise(int idAnalise);

    Analise findById(int idAnalise);

    Analise findByObjetoDeAnalise(String objetoDeAnalise);

    void updateAnalise(Analise analise);
}
