package api.autotam.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Classe modelo referente a Análises TAM.
 * Um objeto dessa classe representa uma Análise TAM no sistema, guardando as informações
 * e uma lista de Variáveis TAM referentes a tal Análise TAM
 *
 * @author Danilo
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

    @OneToMany(mappedBy = "analise",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OrderBy
    @JsonManagedReference
    private Set<VariavelTAM> variaveis;

    @OneToMany(mappedBy = "analise",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OrderBy
    @JsonManagedReference
    private Set<OpcaoDeObjeto> opcoesDeObjeto;

    public Analise(){}

    public Analise( String nome, String objetoDeAnalise, Set<VariavelTAM> variaveis){
        this.nome = nome;
        this.objetoDeAnalise = objetoDeAnalise;
        this.variaveis = variaveis;
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

    public Set<VariavelTAM> getVariaveis() {
        return variaveis;
    }

    public void setVariaveis(Set<VariavelTAM> variaveis) {
        this.variaveis = variaveis;
    }

    public Set<OpcaoDeObjeto> getOpcoesDeObjeto() {
        return opcoesDeObjeto;
    }

    public void setOpcoesDeObjeto(Set<OpcaoDeObjeto> opcoesDeObjeto) {
        this.opcoesDeObjeto = opcoesDeObjeto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Analise)) return false;
        Analise analise = (Analise) o;
        return Objects.equals(getIdAnalise(), analise.getIdAnalise()) &&
                Objects.equals(getNome(), analise.getNome()) &&
                Objects.equals(getObjetoDeAnalise(), analise.getObjetoDeAnalise()) &&
                Objects.equals(getStatus(), analise.getStatus()) &&
                Objects.equals(getVariaveis(), analise.getVariaveis()) &&
                Objects.equals(getOpcoesDeObjeto(), analise.getOpcoesDeObjeto());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdAnalise(), getNome(), getObjetoDeAnalise(), getStatus());
    }

    @Override
    public String toString() {
        return "Analise{" +
                "idAnalise=" + idAnalise +
                ", nome='" + nome + '\'' +
                ", objetoDeAnalise='" + objetoDeAnalise + '\'' +
                ", status='" + status + '\'' +
                ", variaveis=" + variaveis.size() +
                ", opcoesDeObjeto=" + opcoesDeObjeto.size() +
                '}';
    }
}
