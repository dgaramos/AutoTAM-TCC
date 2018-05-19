package api.autotam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

/**
 * Classe modelo referente as Variáveis TAM.
 * Um objeto dessa classe mapeia uma Variável TAM, fazendo referência a qual Análise TAM ela faz parte
 * e guardando uma lista com todas as suas Questões.
 *
 * @author Danilo
 */

@Entity
@Table(name= "variavelTAM")
public class VariavelTAM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVariavel")
    private Integer idVariavel;

    @Column(name = "nomeVariavel")
    private String nomeVariavel;

    @Column(name = "variavelPadrao")
    private boolean variavelPadrao;

    @Column(name = "nota")
    private double nota;

    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "idAnalise")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Analise analise;

    @OneToMany(mappedBy = "variavelTAM", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<Questao> questoes;

    public VariavelTAM(){}

    public VariavelTAM( String nomeVariavel, boolean variavelPadrao, Analise analise){
        this.nomeVariavel = nomeVariavel;
        this.variavelPadrao = variavelPadrao;
        this.analise = analise;
        this.nota = 0;
    }

    public Integer getIdVariavel() {
        return idVariavel;
    }

    public void setIdVariavel(Integer idVariavel) {
        this.idVariavel = idVariavel;
    }

    public String getNomeVariavel() {
        return nomeVariavel;
    }

    public void setNomeVariavel(String nomeVariavel) {
        this.nomeVariavel = nomeVariavel;
    }

    public boolean isVariavelPadrao() {
        return variavelPadrao;
    }

    public void setVariavelPadrao(boolean variavelPadrao) {
        this.variavelPadrao = variavelPadrao;
    }

    public Analise getAnalise() {
        return analise;
    }

    public void setAnalise(Analise analise) {
        this.analise = analise;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
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

        if (variavelPadrao != that.variavelPadrao) return false;
        if (Double.compare(that.nota, nota) != 0) return false;
        if (idVariavel != null ? !idVariavel.equals(that.idVariavel) : that.idVariavel != null) return false;
        if (nomeVariavel != null ? !nomeVariavel.equals(that.nomeVariavel) : that.nomeVariavel != null) return false;
        if (analise != null ? !analise.equals(that.analise) : that.analise != null) return false;
        return questoes != null ? questoes.equals(that.questoes) : that.questoes == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idVariavel != null ? idVariavel.hashCode() : 0;
        result = 31 * result + (nomeVariavel != null ? nomeVariavel.hashCode() : 0);
        result = 31 * result + (variavelPadrao ? 1 : 0);
        result = 31 * result + (analise != null ? analise.hashCode() : 0);
        result = 31 * result + (questoes != null ? questoes.hashCode() : 0);
        temp = Double.doubleToLongBits(nota);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "VariavelTAM{" +
                "idVariavel=" + idVariavel +
                ", nomeVariavel='" + nomeVariavel + '\'' +
                ", variavelPadrao=" + variavelPadrao +
                ", analise=" + analise +
                ", questoes=" + questoes.size() +
                ", nota=" + nota +
                '}';
    }
}
