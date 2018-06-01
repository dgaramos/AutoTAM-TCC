package api.autotam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name= "resultadoOpcaoQuestao")
public class ResultadoOpcaoQuestao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idResultadoOpcaoQuestao")
    private Integer idResultadoOpcaoQuestao;

    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "idQuestao")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Questao questao;

    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "idOpcaoDeObjeto")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference (value="opcaoDeObjetoToResultadoOpcaoQuestao")
    private OpcaoDeObjeto opcaoDeObjeto;

    @Column(name = "notaOpcaoQuestao")
    private Integer notaOpcaoQuestao;

    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "idResultadoOpcaoVariavel")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference (value="resultadoOpcaoVariavelToResultadoOpcaoQuestao")
    private ResultadoOpcaoVariavel resultadoOpcaoVariavel;

    public Integer getIdResultadoOpcaoQuestao() {
        return idResultadoOpcaoQuestao;
    }

    public void setIdResultadoOpcaoQuestao(Integer idResultadoOpcaoQuestao) {
        this.idResultadoOpcaoQuestao = idResultadoOpcaoQuestao;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }

    public OpcaoDeObjeto getOpcaoDeObjeto() {
        return opcaoDeObjeto;
    }

    public void setOpcaoDeObjeto(OpcaoDeObjeto opcaoDeObjeto) {
        this.opcaoDeObjeto = opcaoDeObjeto;
    }

    public Integer getNotaOpcaoQuestao() {
        return notaOpcaoQuestao;
    }

    public void setNotaOpcaoQuestao(Integer notaOpcaoQuestao) {
        this.notaOpcaoQuestao = notaOpcaoQuestao;
    }

    public ResultadoOpcaoVariavel getResultadoOpcaoVariavel() {
        return resultadoOpcaoVariavel;
    }

    public void setResultadoOpcaoVariavel(ResultadoOpcaoVariavel resultadoOpcaoVariavel) {
        this.resultadoOpcaoVariavel = resultadoOpcaoVariavel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultadoOpcaoQuestao)) return false;
        ResultadoOpcaoQuestao that = (ResultadoOpcaoQuestao) o;
        return Objects.equals(getIdResultadoOpcaoQuestao(), that.getIdResultadoOpcaoQuestao()) &&
                Objects.equals(getQuestao(), that.getQuestao()) &&
                Objects.equals(getOpcaoDeObjeto(), that.getOpcaoDeObjeto()) &&
                Objects.equals(getNotaOpcaoQuestao(), that.getNotaOpcaoQuestao()) &&
                Objects.equals(getResultadoOpcaoVariavel(), that.getResultadoOpcaoVariavel());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdResultadoOpcaoQuestao(), getQuestao(), getOpcaoDeObjeto(), getNotaOpcaoQuestao(), getResultadoOpcaoVariavel());
    }

    @Override
    public String toString() {
        return "ResultadoOpcaoQuestao{" +
                "idResultadoOpcaoQuestao=" + idResultadoOpcaoQuestao +
                ", questao=" + questao +
                ", opcaoDeObjeto=" + opcaoDeObjeto +
                ", notaOpcaoQuestao=" + notaOpcaoQuestao +
                ", resultadoOpcaoVariavel=" + resultadoOpcaoVariavel +
                '}';
    }
}
