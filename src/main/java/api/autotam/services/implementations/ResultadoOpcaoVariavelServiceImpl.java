package api.autotam.services.implementations;

import api.autotam.daos.interfaces.ResultadoOpcaoVariavelDAO;
import api.autotam.model.OpcaoDeObjeto;
import api.autotam.model.ResultadoOpcaoQuestao;
import api.autotam.model.ResultadoOpcaoVariavel;
import api.autotam.model.VariavelTAM;
import api.autotam.services.interfaces.ResultadoOpcaoVariavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service("resultadoOpcaoVariavelService")
@Transactional
public class ResultadoOpcaoVariavelServiceImpl extends AbstractService implements ResultadoOpcaoVariavelService {

    @Autowired
    private ResultadoOpcaoVariavelDAO resultadoOpcaoVariavelDAO;

    @Override
    public void saveResultadoOpcaoVariavel(ResultadoOpcaoVariavel resultadoOpcaoVariavel) {
        resultadoOpcaoVariavelDAO.saveResultadoOpcaoVariavel(resultadoOpcaoVariavel);
    }

    @Override
    public void updateResultadoOpcaoVariavel(ResultadoOpcaoVariavel resultadoOpcaoVariavel) {
        resultadoOpcaoVariavelDAO.updateResultadoOpcaoVariavel(resultadoOpcaoVariavel);
    }

    @Override
    public void deleteResultadoOpcaoVariavel(int idResultadoOpcaoVariavel) {
        resultadoOpcaoVariavelDAO.deleteResultadoOpcaoVariavel(idResultadoOpcaoVariavel);
    }

    @Override
    public ResultadoOpcaoVariavel findById(int idResultadoOpcaoVariavel) {
        return resultadoOpcaoVariavelDAO.findById(idResultadoOpcaoVariavel);
    }

    @Override
    public void calcularResultadoVariavel(OpcaoDeObjeto opcaoDeObjeto, VariavelTAM variavel) {
        ResultadoOpcaoVariavel resultadoOpcaoVariavel =
                resultadoOpcaoVariavelDAO.findFromOpcaoVariavel(
                        opcaoDeObjeto.getIdOpcaoDeObjeto(), variavel.getIdVariavel());

        int somatorioRespostas = 0;
        for(ResultadoOpcaoQuestao resultadoOpcaoQuestao : resultadoOpcaoVariavel.getResultadosOpcaoQuestao()){
            somatorioRespostas = somatorioRespostas + resultadoOpcaoQuestao.getNotaOpcaoQuestao();
        }
        resultadoOpcaoVariavel.setNotaOpcaoVariavel(
                somatorioRespostas/resultadoOpcaoVariavel.getResultadosOpcaoQuestao().size());
        resultadoOpcaoVariavelDAO.saveResultadoOpcaoVariavel(resultadoOpcaoVariavel);
    }

    @Override
    public List<ResultadoOpcaoVariavel> findFromOpcao(int idOpcaoDeObjeto) {
        return resultadoOpcaoVariavelDAO.findFromOpcao(idOpcaoDeObjeto);
    }
}
