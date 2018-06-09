package api.autotam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name= "resultadoOpcaoVariavel")
public class ResultadoOpcaoVariavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idResultadoOpcaoVariavel")
    private Integer idResultadoOpcaoVariavel;

    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "idVariavel")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private VariavelTAM variavelTAM;

    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "idOpcaoDeObjeto")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference(value="opcaoDeObjetoToResultadoOpcaoVariavel")
    private OpcaoDeObjeto opcaoDeObjeto;

    @Column(name = "notaOpcaoVariavel")
    private Double notaOpcaoVariavel;

    @OneToMany(mappedBy = "resultadoOpcaoVariavel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference(value="resultadoOpcaoVariavelToResultadoOpcaoQuestao")
    private List<ResultadoOpcaoQuestao> resultadosOpcaoQuestao;

    public Integer getIdResultadoOpcaoVariavel() {
        return idResultadoOpcaoVariavel;
    }

    public void setIdResultadoOpcaoVariavel(Integer idResultadoOpcaoVariavel) {
        this.idResultadoOpcaoVariavel = idResultadoOpcaoVariavel;
    }

    public VariavelTAM getVariavelTAM() {
        return variavelTAM;
    }

    public void setVariavelTAM(VariavelTAM variavelTAM) {
        this.variavelTAM = variavelTAM;
    }

    public OpcaoDeObjeto getOpcaoDeObjeto() {
        return opcaoDeObjeto;
    }

    public void setOpcaoDeObjeto(OpcaoDeObjeto opcaoDeObjeto) {
        this.opcaoDeObjeto = opcaoDeObjeto;
    }

    public Double getNotaOpcaoVariavel() {
        return notaOpcaoVariavel;
    }

    public void setNotaOpcaoVariavel(Double notaOpcaoVariavel) {
        this.notaOpcaoVariavel = notaOpcaoVariavel;
    }

    public List<ResultadoOpcaoQuestao> getResultadosOpcaoQuestao() {
        return resultadosOpcaoQuestao;
    }

    public void setResultadosOpcaoQuestao(List<ResultadoOpcaoQuestao> resultadosOpcaoQuestao) {
        this.resultadosOpcaoQuestao = resultadosOpcaoQuestao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultadoOpcaoVariavel)) return false;
        ResultadoOpcaoVariavel that = (ResultadoOpcaoVariavel) o;
        return Objects.equals(getIdResultadoOpcaoVariavel(), that.getIdResultadoOpcaoVariavel()) &&
                Objects.equals(getVariavelTAM(), that.getVariavelTAM()) &&
                Objects.equals(getOpcaoDeObjeto(), that.getOpcaoDeObjeto()) &&
                Objects.equals(getNotaOpcaoVariavel(), that.getNotaOpcaoVariavel()) &&
                Objects.equals(getResultadosOpcaoQuestao(), that.getResultadosOpcaoQuestao());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdResultadoOpcaoVariavel(), getVariavelTAM(), getOpcaoDeObjeto(), getNotaOpcaoVariavel(), getResultadosOpcaoQuestao());
    }

    @Override
    public String toString() {
        return "ResultadoOpcaoVariavel{" +
                "idResultadoOpcaoVariavel=" + idResultadoOpcaoVariavel +
                ", variavelTAM=" + variavelTAM +
                ", opcaoDeObjeto=" + opcaoDeObjeto +
                ", notaOpcaoVariavel=" + notaOpcaoVariavel +
                ", resultadosOpcaoQuestao=" + resultadosOpcaoQuestao +
                '}';
    }
}
