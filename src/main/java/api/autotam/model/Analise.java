package api.autotam.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Danilo on 9/11/2016.
 */

@Entity
@Table(name= "analise")
public class Analise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAnalise")
    private Integer idAnalise;

    @Column(name = "nome")
    private String nome;

    @Column(name = "objetoDeAnalise")
    private String objetoDeAnalise;

    @Column(name = "status")
    private String status;

    public Integer getIdAnalise() {
        return idAnalise;
    }

    public void setIdAnalise(Integer idAnalise) {
        this.idAnalise = idAnalise;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObjetoDeAnalise() {
        return objetoDeAnalise;
    }

    public void setObjetoDeAnalise(String objetoDeAnalise) {
        this.objetoDeAnalise = objetoDeAnalise;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Analise)) return false;

        Analise analise = (Analise) o;

        if (getIdAnalise() != null ? !getIdAnalise().equals(analise.getIdAnalise()) : analise.getIdAnalise() != null)
            return false;
        if (getNome() != null ? !getNome().equals(analise.getNome()) : analise.getNome() != null) return false;
        if (getObjetoDeAnalise() != null ? !getObjetoDeAnalise().equals(analise.getObjetoDeAnalise()) : analise.getObjetoDeAnalise() != null)
            return false;
        return getStatus() != null ? getStatus().equals(analise.getStatus()) : analise.getStatus() == null;

    }

    @Override
    public int hashCode() {
        int result = getIdAnalise() != null ? getIdAnalise().hashCode() : 0;
        result = 31 * result + (getNome() != null ? getNome().hashCode() : 0);
        result = 31 * result + (getObjetoDeAnalise() != null ? getObjetoDeAnalise().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Analise{" +
                "idAnalise=" + idAnalise +
                ", nome='" + nome + '\'' +
                ", objetoDeAnalise='" + objetoDeAnalise + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}