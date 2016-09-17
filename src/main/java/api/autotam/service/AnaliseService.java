package api.autotam.service;

import api.autotam.model.Analise;
import api.autotam.model.Permissao;
import api.autotam.model.Usuario;

import java.util.List;

/**
 * Created by Danilo on 9/12/2016.
 */
public interface AnaliseService {

    void saveAnalise(Analise analise);

    List<Permissao> findAllAnalises(int id);

    Analise findById(Integer id);

    boolean isAnaliseExist(Analise analise);


}
