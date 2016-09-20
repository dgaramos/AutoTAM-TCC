package api.autotam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Created by danilo on 20/09/16.
 */


@Entity
@Table(name= "variavelTAM")
public class VariavelTAM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVariavel")
    private Integer idVariavel;

    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "idAnalise")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Analise analise;

    @Column(name = "nota")
    private double nota;

    public Integer getIdVariavel() {
        return idVariavel;
    }

    public void setIdVariavel(Integer idVariavel) {
        this.idVariavel = idVariavel;
    }

    public Analise getAnalise() {
        return analise;
    }

    public void setAnalise(Analise analise) {
        this.analise = analise;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VariavelTAM that = (VariavelTAM) o;

        if (Double.compare(that.nota, nota) != 0) return false;
        if (idVariavel != null ? !idVariavel.equals(that.idVariavel) : that.idVariavel != null) return false;
        return analise != null ? analise.equals(that.analise) : that.analise == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idVariavel != null ? idVariavel.hashCode() : 0;
        result = 31 * result + (analise != null ? analise.hashCode() : 0);
        temp = Double.doubleToLongBits(nota);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "VariavelTAM{" +
                "idVariavel=" + idVariavel +
                ", analise=" + analise +
                ", nota=" + nota +
                '}';
    }
}
