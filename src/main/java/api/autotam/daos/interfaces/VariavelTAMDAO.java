package api.autotam.daos.interfaces;

import api.autotam.model.VariavelTAM;

import java.util.List;

/**
 * Interface responsável por encapsular as assinaturas dos métodos das operações referentes aos registros no banco de
 * dados da classe Variável TAM.
 *
 * @author Danilo
 */

public interface VariavelTAMDAO {

    void saveVariavel(VariavelTAM variavel);

    VariavelTAM findById(int idVariavel);

    void updateVariavel(VariavelTAM variavel);

    void deleteVariavel(int idVariavel);

    List<VariavelTAM> findAllVariaveisFromAnalise(int idAnalise);

}
