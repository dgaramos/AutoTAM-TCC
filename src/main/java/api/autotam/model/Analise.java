package api.autotam.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @Column(name = "tipoAnalise")
    private String tipoAnalise;

    @Column(name = "objetoDeAnalise")
    private String objetoDeAnalise;

    @OneToMany(mappedBy = "analise", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<VariavelTAM> variaveis;

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

    public String getTipoAnalise() {
        return tipoAnalise;
    }

    public void setTipoAnalise(String tipoAnalise) {
        this.tipoAnalise = tipoAnalise;
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
        if (o == null || getClass() != o.getClass()) return false;

        Analise analise = (Analise) o;

        if (idAnalise != null ? !idAnalise.equals(analise.idAnalise) : analise.idAnalise != null) return false;
        if (nome != null ? !nome.equals(analise.nome) : analise.nome != null) return false;
        if (tipoAnalise != null ? !tipoAnalise.equals(analise.tipoAnalise) : analise.tipoAnalise != null) return false;
        if (objetoDeAnalise != null ? !objetoDeAnalise.equals(analise.objetoDeAnalise) : analise.objetoDeAnalise != null)
            return false;
        if (variaveis != null ? !variaveis.equals(analise.variaveis) : analise.variaveis != null) return false;
        return status != null ? status.equals(analise.status) : analise.status == null;

    }

    @Override
    public int hashCode() {
        int result = idAnalise != null ? idAnalise.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (tipoAnalise != null ? tipoAnalise.hashCode() : 0);
        result = 31 * result + (objetoDeAnalise != null ? objetoDeAnalise.hashCode() : 0);
        result = 31 * result + (variaveis != null ? variaveis.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Analise{" +
                "idAnalise=" + idAnalise +
                ", nome='" + nome + '\'' +
                ", tipoAnalise='" + tipoAnalise + '\'' +
                ", objetoDeAnalise='" + objetoDeAnalise + '\'' +
                ", variaveis=" + variaveis +
                ", status='" + status + '\'' +
                '}';
    }
}
