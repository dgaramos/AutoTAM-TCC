package api.autotam.daos.interfaces;

import api.autotam.model.Questionario;

public interface QuestionarioDAO {

    void saveQuestionario(Questionario questionario);

    Questionario findById(int idQuestionario);

    void updateQuestionario(Questionario questionario);

    void deleteQuestionario(int idQuestionario);

}
