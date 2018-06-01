package api.autotam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Danilo on 11/10/2017.
 */

@Entity
@Table(name= "questionario")
public class Questionario implements Serializable {

    //Constantes
    public static final int AVALIACAO_DE_OBJETO = 1;
    public static final int PESQUISA_DE_PUBLICO = 2;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idQuestionario")
    private Integer idQuestionario;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAnalise")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Analise analise;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "idOpcaoDeObjeto")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private OpcaoDeObjeto opcaoDeObjeto;

    @OneToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;

    @Column(name = "tipo")
    private Integer tipo;

    public Integer getIdQuestionario() {
        return idQuestionario;
    }

    public void setIdQuestionario(Integer idQuestionario) {
        this.idQuestionario = idQuestionario;
    }

    public Analise getAnalise() {
        return analise;
    }

    public void setAnalise(Analise analise) {
        this.analise = analise;
    }

    public OpcaoDeObjeto getOpcaoDeObjeto() {
        return opcaoDeObjeto;
    }

    public void setOpcaoDeObjeto(OpcaoDeObjeto opcaoDeObjeto) {
        this.opcaoDeObjeto = opcaoDeObjeto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Questionario)) return false;
        Questionario that = (Questionario) o;
        return Objects.equals(getIdQuestionario(), that.getIdQuestionario()) &&
                Objects.equals(getAnalise(), that.getAnalise()) &&
                Objects.equals(getOpcaoDeObjeto(), that.getOpcaoDeObjeto()) &&
                Objects.equals(getUsuario(), that.getUsuario()) &&
                Objects.equals(getTipo(), that.getTipo());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdQuestionario(), getAnalise(), getOpcaoDeObjeto(), getUsuario(), getTipo());
    }

    @Override
    public String toString() {
        return "Questionario{" +
                "idQuestionario=" + idQuestionario +
                ", analise=" + analise +
                ", opcaoDeObjeto=" + opcaoDeObjeto +
                ", usuario=" + usuario +
                ", tipo=" + tipo +
                '}';
    }
}
