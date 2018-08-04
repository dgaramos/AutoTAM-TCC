package api.autotam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;

/**
 * Classe modelo referente as Questões.
 * Um objeto dessa classe mapeia uma Questão inclusa dentro de uma Variável TAM.
 *
 * @author Danilo
 */

@Entity
@Table(name= "questao")
public class Questao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idQuestao")
    private Integer idQuestao;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "enunciado")
    private String enunciado;

    @OneToMany(mappedBy = "questao",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference(value="questaoToRespostas")
    private List<Resposta> respostas;

    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "idVariavel")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference(value="variavelToQuestoes")
    private VariavelTAM variavelTAM;

    public Questao(){}

    public Questao(Integer numero, String enunciado, VariavelTAM variavelTAM){
        this.numero = numero;
        this.enunciado = enunciado;
        this.variavelTAM = variavelTAM;
    }

    public Integer getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(Integer idQuestao) {
        this.idQuestao = idQuestao;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public VariavelTAM getVariavelTAM() {
        return variavelTAM;
    }

    public void setVariavelTAM(VariavelTAM variavelTAM) {
        this.variavelTAM = variavelTAM;
    }

    public List<Resposta> getRespostas() {

        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Questao)) return false;
        Questao questao = (Questao) o;
        return Objects.equals(getIdQuestao(), questao.getIdQuestao()) &&
                Objects.equals(getNumero(), questao.getNumero()) &&
                Objects.equals(getEnunciado(), questao.getEnunciado()) &&
                Objects.equals(getVariavelTAM(), questao.getVariavelTAM());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdQuestao(), getNumero(), getEnunciado());
    }

    @Override
    public String toString() {
        return "Questao{" +
                "idQuestao=" + idQuestao +
                ", numero=" + numero +
                ", enunciado='" + enunciado + '\'' +
                ", respostas=" + respostas.size() +
                ", variavelTAM=" + variavelTAM +
                '}';
    }

}
