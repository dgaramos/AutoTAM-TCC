package api.autotam.service;

import api.autotam.model.Analise;
import api.autotam.model.Permissao;
import api.autotam.model.Usuario;
import api.autotam.model.VariavelTAM;

import java.util.List;

/**
 * Created by Danilo on 9/12/2016.
 */
public interface AnaliseService {

    void saveAnalise(Analise analise);

    void savePermissao(Permissao permissao);

    void addVariavelToAnalise(int idAnalise, VariavelTAM variavel);

    List<Permissao> findAllAnalises(int idUsuario);

    List<Permissao> findAllPermissoesFromAnalise(int idAnalise);

    Analise findById(int idAnalise);

    void deleteAnalise(int idAnalise);

    void updateAnalise(Analise analise);

    boolean isAnaliseExist(Analise analise);

}
