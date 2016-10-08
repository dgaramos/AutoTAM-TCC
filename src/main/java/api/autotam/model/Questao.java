package api.autotam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

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

    @Column(name ="peso")
    private double peso;

    @Column(name ="resposta")
    private double resposta;

    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "idVariavel")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private VariavelTAM variavelTAM;

    public Questao(){}

    public Questao( String enunciado, VariavelTAM variavelTAM){
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

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getResposta() {
        return resposta;
    }

    public void setResposta(double resposta) {
        this.resposta = resposta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Questao)) return false;

        Questao questao = (Questao) o;

        if (Double.compare(questao.getPeso(), getPeso()) != 0) return false;
        if (Double.compare(questao.getResposta(), getResposta()) != 0) return false;
        if (getIdQuestao() != null ? !getIdQuestao().equals(questao.getIdQuestao()) : questao.getIdQuestao() != null)
            return false;
        if (getNumero() != null ? !getNumero().equals(questao.getNumero()) : questao.getNumero() != null) return false;
        if (getEnunciado() != null ? !getEnunciado().equals(questao.getEnunciado()) : questao.getEnunciado() != null)
            return false;
        return getVariavelTAM() != null ? getVariavelTAM().equals(questao.getVariavelTAM()) : questao.getVariavelTAM() == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getIdQuestao() != null ? getIdQuestao().hashCode() : 0;
        result = 31 * result + (getNumero() != null ? getNumero().hashCode() : 0);
        result = 31 * result + (getEnunciado() != null ? getEnunciado().hashCode() : 0);
        temp = Double.doubleToLongBits(getPeso());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getResposta());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getVariavelTAM() != null ? getVariavelTAM().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Questao{" +
                "idQuestao=" + idQuestao +
                ", numero=" + numero +
                ", enunciado='" + enunciado + '\'' +
                ", peso=" + peso +
                ", resposta=" + resposta +
                ", variavelTAM=" + variavelTAM +
                '}';
    }
}
