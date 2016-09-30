package api.autotam.services.interfaces;

import api.autotam.model.Questao;
import api.autotam.model.VariavelTAM;

import java.util.List;

/**
 *  Interface responsável por encapsular as assinaturas dos métodos de regra de negócio referentes a classe
 *  Variável TAM.
 *
 * @author Danilo
 */
public interface VariavelTAMService {

    void saveVariavel(VariavelTAM variavel);

    VariavelTAM findById(int idVariavel);

    void updateVariavel(VariavelTAM variavel);

    void deleteVariavel(int idVariavel);

    boolean isVariavelExist(VariavelTAM variavel);

    List<VariavelTAM> findAllVariaveisFromAnalise(int idAnalise);

}
