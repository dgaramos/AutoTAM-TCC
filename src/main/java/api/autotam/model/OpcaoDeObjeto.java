package api.autotam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


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
    @JsonManagedReference(value="opcaoDeObjetoToResultadoOpcaoVariavel")
    private List<ResultadoOpcaoVariavel> resultadosOpcaoVariaveis;

    @OneToMany(mappedBy = "opcaoDeObjeto",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference(value="opcaoDeObjetoToResultadoOpcaoQuestao")
    private List<ResultadoOpcaoQuestao> resultadosOpcaoQuestao;

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

    public List<ResultadoOpcaoVariavel> getResultadosOpcaoVariaveis() {
        return resultadosOpcaoVariaveis;
    }

    public void setResultadosOpcaoVariaveis(List<ResultadoOpcaoVariavel> resultadosOpcaoVariaveis) {
        this.resultadosOpcaoVariaveis = resultadosOpcaoVariaveis;
    }

    public List<ResultadoOpcaoQuestao> getResultadosOpcaoQuestao() {
        return resultadosOpcaoQuestao;
    }

    public void setResultadosOpcaoQuestao(List<ResultadoOpcaoQuestao> resultadosOpcaoQuestao) {
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
