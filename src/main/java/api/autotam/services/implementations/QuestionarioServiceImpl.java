package api.autotam.services.implementations;

import api.autotam.daos.interfaces.QuestionarioDAO;
import api.autotam.model.Questionario;
import api.autotam.services.interfaces.QuestionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service("questionarioService")
@Transactional
public class QuestionarioServiceImpl extends AbstractService implements QuestionarioService {

    @Autowired
    private QuestionarioDAO questionarioDAO;


    @Override
    public void saveQuestionario(Questionario questionario) {
        questionarioDAO.saveQuestionario(questionario);
    }

    @Override
    public void updateQuestionario(Questionario questionario) {
        questionarioDAO.updateQuestionario(questionario);
    }

    @Override
    public void deleteQuestionario(int idQuestionario) {
        questionarioDAO.deleteQuestionario(idQuestionario);
    }

    @Override
    public Questionario findById(int idQuestionario) {
        return questionarioDAO.findById(idQuestionario);
    }

    @Override
    public boolean usuarioJaRespondeuOpcaoDeObjeto(int idUsuario, int idOpcaoDeObjeto, int idAnalise) {
        if(questionarioDAO.findByUsuarioOpcaoDeObjetoAnalise(idUsuario,idOpcaoDeObjeto,idAnalise)== null){
            System.out.println(idUsuario + " " + idOpcaoDeObjeto + " " + idAnalise);
            return false;
        }
        return true;
    }

    @Override
    public Integer quantidadeQuestionariosByOpcaoDeObjeto(int idOpcaoDeObjeto, int idAnalise) {
        return questionarioDAO.findByOpcaoDeObjetoAnalise(idOpcaoDeObjeto, idAnalise).size();
    }

    @Override
    public List<Questionario> questionariosByOpcaoDeObjeto(int idOpcaoDeObjeto, int idAnalise) {
        return questionarioDAO.findByOpcaoDeObjetoAnalise(idOpcaoDeObjeto, idAnalise);
    }
}
