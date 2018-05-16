package api.autotam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
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
    @JsonBackReference
    private OpcaoDeObjeto opcaoDeObjeto;

    @Column(name = "notaVariavelObjeto")
    private Integer notaVariavelObjeto;

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

    public Integer getNotaVariavelObjeto() {
        return notaVariavelObjeto;
    }

    public void setNotaVariavelObjeto(Integer notaVariavelObjeto) {
        this.notaVariavelObjeto = notaVariavelObjeto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultadoOpcaoVariavel)) return false;
        ResultadoOpcaoVariavel that = (ResultadoOpcaoVariavel) o;
        return Objects.equals(getIdResultadoOpcaoVariavel(), that.getIdResultadoOpcaoVariavel()) &&
                Objects.equals(getVariavelTAM(), that.getVariavelTAM()) &&
                Objects.equals(getOpcaoDeObjeto(), that.getOpcaoDeObjeto()) &&
                Objects.equals(getNotaVariavelObjeto(), that.getNotaVariavelObjeto());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdResultadoOpcaoVariavel(), getVariavelTAM(), getOpcaoDeObjeto(), getNotaVariavelObjeto());
    }

    @Override
    public String toString() {
        return "ResultadoOpcaoVariavel{" +
                "idResultadoOpcaoVariavel=" + idResultadoOpcaoVariavel +
                ", variavelTAM=" + variavelTAM +
                ", opcaoDeObjeto=" + opcaoDeObjeto +
                ", notaVariavelObjeto=" + notaVariavelObjeto +
                '}';
    }
}
