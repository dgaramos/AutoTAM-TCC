package api.autotam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name= "opcaoDeObjeto")
public class OpcaoDeObjeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOpcaoDeObjeto")
    private Integer idOpcaoDeObjeto;

    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "opcaoDeObjeto",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OrderBy (value="idVariavel")
    @JsonManagedReference(value="opcaoDeObjetoToResultadoOpcaoVariavel")
    private Set<ResultadoOpcaoVariavel> resultadosOpcaoVariaveis;

    @OneToMany(mappedBy = "opcaoDeObjeto",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OrderBy (value="idQuestao")
    @JsonManagedReference(value="opcaoDeObjetoToResultadoOpcaoQuestao")
    private Set<ResultadoOpcaoQuestao> resultadosOpcaoQuestao;

    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "idAnalise")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference(value="analiseToOpcaoDeObjeto")
    private Analise analise;

    public Integer getIdOpcaoDeObjeto() {
        return idOpcaoDeObjeto;
    }

    public void setIdOpcaoDeObjeto(Integer idOpcaoDeObjeto) {
        this.idOpcaoDeObjeto = idOpcaoDeObjeto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<ResultadoOpcaoVariavel> getResultadosOpcaoVariaveis() {
        return resultadosOpcaoVariaveis;
    }

    public void setResultadosOpcaoVariaveis(Set<ResultadoOpcaoVariavel> resultadosOpcaoVariaveis) {
        this.resultadosOpcaoVariaveis = resultadosOpcaoVariaveis;
    }

    public Set<ResultadoOpcaoQuestao> getResultadosOpcaoQuestao() {
        return resultadosOpcaoQuestao;
    }

    public void setResultadosOpcaoQuestao(Set<ResultadoOpcaoQuestao> resultadosOpcaoQuestao) {
        this.resultadosOpcaoQuestao = resultadosOpcaoQuestao;
    }

    public Analise getAnalise() {
        return analise;
    }

    public void setAnalise(Analise analise) {
        this.analise = analise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OpcaoDeObjeto)) return false;
        OpcaoDeObjeto that = (OpcaoDeObjeto) o;
        return Objects.equals(getIdOpcaoDeObjeto(), that.getIdOpcaoDeObjeto()) &&
                Objects.equals(getNome(), that.getNome()) &&
                Objects.equals(getAnalise(), that.getAnalise());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdOpcaoDeObjeto(), getNome(), getAnalise());
    }

    @Override
    public String toString() {
        return "OpcaoDeObjeto{" +
                "idOpcaoDeObjeto=" + idOpcaoDeObjeto +
                ", nome='" + nome + '\'' +
                ", resultadosOpcaoVariaveis=" + resultadosOpcaoVariaveis +
                ", resultadosOpcaoQuestao=" + resultadosOpcaoQuestao +
                ", analise=" + analise +
                '}';
    }
}
