package api.autotam.services.interfaces;

import api.autotam.model.Questionario;

import java.util.List;

public interface QuestionarioService {

    void saveQuestionario(Questionario questionario);

    void updateQuestionario(Questionario questionario);

    void deleteQuestionario (int idQuestionario);

    Questionario findById(int idQuestionario);

    boolean usuarioJaRespondeuOpcaoDeObjeto(int idUsuario, int idOpcaoDeObjeto, int idAnalise);

    Integer quantidadeQuestionariosByOpcaoDeObjeto(int idOpcaoDeObjeto, int idAnalise);

    List<Questionario> questionariosByOpcaoDeObjeto(int idOpcaoDeObjeto, int idAnalise);

}
