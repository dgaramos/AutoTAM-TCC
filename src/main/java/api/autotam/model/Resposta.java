package api.autotam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Danilo on 11/10/2017.
 */

@Entity
@Table(name= "resposta")
public class Resposta implements Serializable {

    //Constantes Likert

    public static final int DISCORDO_TOTALMENTE = 1;
    public static final int DISCORDO_PARCIALMENTE = 2;
    public static final int INDIFERENTE = 3;
    public static final int CONCORDO_TOTALMENTE = 4;
    public static final int CONCORDO_PARCIALMENTE = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idResposta")
    private Integer idResposta;

    @ManyToOne(cascade = CascadeType.MERGE  )
    @JoinColumn(name = "idQuestao",  nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference(value="questaoToRespostas")
    private Questao questao;

    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "idQuestionario")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Questionario questionario;

    @Column(name = "resposta")
    private Double resposta;

    public Resposta(){

    }

    public Resposta(int resposta){
        this.resposta = (Double) resposta;
    }

    public Integer getIdResposta() {
        return idResposta;
    }

    public void setIdResposta(Integer idResposta) {
        this.idResposta = idResposta;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

    public Double getResposta() {
        return resposta;
    }

    public void setResposta(Double resposta) {
        this.resposta = resposta;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resposta)) return false;
        Resposta resposta1 = (Resposta) o;
        return Objects.equals(getIdResposta(), resposta1.getIdResposta()) &&
                Objects.equals(getQuestao(), resposta1.getQuestao()) &&
                Objects.equals(getQuestionario(), resposta1.getQuestionario()) &&
                Objects.equals(getResposta(), resposta1.getResposta());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdResposta(), getQuestao(), getQuestionario(), getResposta());
    }

    @Override
    public String toString() {
        return "Resposta{" +
                "idResposta=" + idResposta +
                ", questao=" + questao +
                ", questionario=" + questionario +
                ", resposta=" + resposta +
                '}';
    }
}
