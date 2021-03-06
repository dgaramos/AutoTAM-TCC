package api.autotam.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Classe modelo referente as Permissões de Usuário.
 * Um objeto dessa classe mapeia um Usuário e uma Análise representando assim que o Usuário mapeado tem acesso
 * aos dados da Análise mapeada nesta classe, além disso ela determina se o tipo de acesso do Usuário
 * é de Administrador (quando a variável Administrador é true), Testador (quando a variável Testador
 * é true) ou Observador (quando ambas as variáveis Administrador e Testador são false).
 *
 * @author Danilo
 */

@Entity
@Table(name= "permissao")
public class Permissao implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPermissao")
    private Integer idPermissao;

    @Column(name = "isAdministrador")
    private boolean isAdministrador;

    @Column(name = "isTestador")
    private boolean isTestador;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idAnalise")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Analise analise;


    public Permissao(){}

    public Permissao( Usuario usuario, Analise analise, boolean isAdministrador, boolean isTestador){
        this.isAdministrador = isAdministrador;
        this.isTestador = isTestador;
        this.usuario = usuario;
        this.analise = analise;
    }

    public Integer getIdPermissao() {
        return idPermissao;
    }

    public void setIdPermissao(Integer idPermissao) {
        this.idPermissao = idPermissao;
    }

    public boolean isAdministrador() {
        return isAdministrador;
    }

    public void setAdministrador(boolean administrador) {
        isAdministrador = administrador;
    }

    public boolean isTestador() {
        return isTestador;
    }

    public void setTestador(boolean testador) {
        isTestador = testador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(o instanceof Permissao)) return false;

        Permissao permissao = (Permissao) o;

        if (isAdministrador() != permissao.isAdministrador()) return false;
        if (isTestador() != permissao.isTestador()) return false;
        if (getIdPermissao() != null ? !getIdPermissao().equals(permissao.getIdPermissao()) : permissao.getIdPermissao() != null)
            return false;
        if (getUsuario() != null ? !getUsuario().equals(permissao.getUsuario()) : permissao.getUsuario() != null)
            return false;
        return getAnalise() != null ? getAnalise().equals(permissao.getAnalise()) : permissao.getAnalise() == null;

    }

    @Override
    public int hashCode() {
        int result = getIdPermissao() != null ? getIdPermissao().hashCode() : 0;
        result = 31 * result + (getUsuario() != null ? getUsuario().hashCode() : 0);
        result = 31 * result + (getAnalise() != null ? getAnalise().hashCode() : 0);
        result = 31 * result + (isAdministrador() ? 1 : 0);
        result = 31 * result + (isTestador() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Permissao{" +
                "idPermissao=" + idPermissao +
                ", usuario=" + usuario +
                ", analise=" + analise +
                ", isAdministrador=" + isAdministrador +
                ", isTestador=" + isTestador +
                '}';
    }
}
