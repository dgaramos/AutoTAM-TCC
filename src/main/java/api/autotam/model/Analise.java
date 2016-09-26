package api.autotam.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @OneToMany(mappedBy = "analise",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<VariavelTAM> variaveis;

    @Column(name = "status")
    private String status;

    public Analise(){}

    public Analise( String nome, String objetoDeAnalise){
        this.nome = nome;
        this.objetoDeAnalise = objetoDeAnalise;
    }



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

    public List<VariavelTAM> getVariaveis() {
        return variaveis;
    }

    public void setVariaveis(List<VariavelTAM> variaveis) {
        this.variaveis = variaveis;
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
        if (getVariaveis() != null ? !getVariaveis().equals(analise.getVariaveis()) : analise.getVariaveis() != null)
            return false;
        return getStatus() != null ? getStatus().equals(analise.getStatus()) : analise.getStatus() == null;

    }

    @Override
    public int hashCode() {
        int result = getIdAnalise() != null ? getIdAnalise().hashCode() : 0;
        result = 31 * result + (getNome() != null ? getNome().hashCode() : 0);
        result = 31 * result + (getObjetoDeAnalise() != null ? getObjetoDeAnalise().hashCode() : 0);
        result = 31 * result + (getVariaveis() != null ? getVariaveis().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Analise{" +
                "idAnalise=" + idAnalise +
                ", nome='" + nome + '\'' +
                ", objetoDeAnalise='" + objetoDeAnalise + '\'' +
                ", variaveis=" + variaveis +
                ", status='" + status + '\'' +
                '}';
    }
}
