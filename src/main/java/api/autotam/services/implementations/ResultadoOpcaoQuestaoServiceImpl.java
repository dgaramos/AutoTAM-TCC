package api.autotam.services.implementations;

import api.autotam.daos.interfaces.QuestaoDAO;
import api.autotam.daos.interfaces.ResultadoOpcaoQuestaoDAO;
import api.autotam.daos.interfaces.ResultadoOpcaoVariavelDAO;
import api.autotam.model.*;
import api.autotam.services.interfaces.ResultadoOpcaoQuestaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("resultadoOpcaoQuestaoService")
@Transactional
public class ResultadoOpcaoQuestaoServiceImpl extends AbstractService implements ResultadoOpcaoQuestaoService {


    @Autowired
    private ResultadoOpcaoQuestaoDAO resultadoOpcaoQuestaoDAO;

    @Autowired

    private ResultadoOpcaoVariavelDAO resultadoOpcaoVariavelDAO;

    @Autowired
    private QuestaoDAO questaoDAO;

    @Override
    public void saveResultadoOpcaoQuestao(ResultadoOpcaoQuestao resultadoOpcaoQuestao) {
        resultadoOpcaoQuestaoDAO.saveResultadoOpcaoQuestao(resultadoOpcaoQuestao);
    }

    @Override
    public void updateResultadoOpcaoQuestao(ResultadoOpcaoQuestao resultadoOpcaoQuestao) {
        resultadoOpcaoQuestaoDAO.updateResultadoOpcaoQuestao(resultadoOpcaoQuestao);
    }

    @Override
    public void deleteResultadoOpcaoQuestao(int idResultadoOpcaoQuestao) {
        resultadoOpcaoQuestaoDAO.deleteResultadoOpcaoQuestao(idResultadoOpcaoQuestao);
    }

    @Override
    public ResultadoOpcaoQuestao findById(int idResultadoOpcaoQuestao) {
        return resultadoOpcaoQuestaoDAO.findById(idResultadoOpcaoQuestao);
    }

    @Override
    public List<ResultadoOpcaoQuestao> findAllResultadosOpcaoQuestaoFromResultadoOpcaoVariavel(int idResultadoOpcaoVariavel) {
        return resultadoOpcaoQuestaoDAO.findAllResultadosOpcaoQuestaoFromResultadoOpcaoVariavel(idResultadoOpcaoVariavel);
    }

    @Override
    public void calcularResultadoQuestao(OpcaoDeObjeto opcaoDeObjeto, Questao questao) {

        ResultadoOpcaoQuestao resultadoOpcaoQuestao =
                resultadoOpcaoQuestaoDAO.findFromOpcaoQuestao(
                        opcaoDeObjeto.getIdOpcaoDeObjeto(), questao.getIdQuestao());
        ResultadoOpcaoVariavel resultadoOpcaoVariavel =
                resultadoOpcaoVariavelDAO.findFromOpcaoVariavel(
                        opcaoDeObjeto.getIdOpcaoDeObjeto(), questao.getVariavelTAM().getIdVariavel());

        int somatorioRespostas = 0;

        List<ResultadoOpcaoQuestao> listaResultados = new ArrayList<ResultadoOpcaoQuestao>();

        if (resultadoOpcaoQuestao == null){
            resultadoOpcaoQuestao = new ResultadoOpcaoQuestao();
            resultadoOpcaoQuestao.setOpcaoDeObjeto(opcaoDeObjeto);
            resultadoOpcaoQuestao.setQuestao(questao);
            if (resultadoOpcaoVariavel == null) {
                resultadoOpcaoVariavel = new ResultadoOpcaoVariavel();
                resultadoOpcaoVariavel.setOpcaoDeObjeto(opcaoDeObjeto);
                resultadoOpcaoVariavel.setVariavelTAM(questao.getVariavelTAM());
                resultadoOpcaoVariavel.setResultadosOpcaoQuestao(listaResultados);
            }
            resultadoOpcaoQuestao.setResultadoOpcaoVariavel(resultadoOpcaoVariavel);
        }
        for(Resposta resposta : questao.getRespostas()){
            somatorioRespostas = somatorioRespostas + resposta.getResposta();
        }
        resultadoOpcaoQuestao.setNotaOpcaoQuestao(somatorioRespostas/questao.getRespostas().size());
        listaResultados = resultadoOpcaoVariavel.getResultadosOpcaoQuestao();
        listaResultados.add(resultadoOpcaoQuestao);
        resultadoOpcaoVariavel.setResultadosOpcaoQuestao(listaResultados);

        resultadoOpcaoQuestaoDAO.saveResultadoOpcaoQuestao(resultadoOpcaoQuestao);
        resultadoOpcaoVariavelDAO.saveResultadoOpcaoVariavel(resultadoOpcaoVariavel);
    }
}
