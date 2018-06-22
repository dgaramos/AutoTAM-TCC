package api.autotam.daos.interfaces;

import api.autotam.model.Questionario;

import java.util.List;

public interface QuestionarioDAO {

    void saveQuestionario(Questionario questionario);

    Questionario findById(int idQuestionario);

    void updateQuestionario(Questionario questionario);

    void deleteQuestionario(int idQuestionario);

    Questionario findByUsuarioOpcaoDeObjetoAnalise(int idUsuario, int idOpcaoDeObjeto, int idAnalise);

    List<Questionario> findByOpcaoDeObjetoAnalise(int idOpcaoDeObjeto, int idAnalise);

}
