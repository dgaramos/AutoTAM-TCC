package api.autotam.model;



import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


/**
 * Classe de dominio referente ao usuario, aqui são guardadas as credenciais de acesso
 * (email e senha) e o nome completo do usuario
 * Created by Danilo on 4/17/2016.
 */

@Entity
@Table(name= "USUARIO")
public class Usuario implements Serializable {


    private static final long serialVersionUID = -3756253191774836358L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL", unique=true)
    private String email;

    @Column(name = "SENHA")
    private String senha;


    public Usuario(){}

    public Usuario( String email, String senha){
        this.email = email;
        this.senha = senha;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;

        Usuario usuario = (Usuario) o;

        if (getIdUsuario() != null ? !getIdUsuario().equals(usuario.getIdUsuario()) : usuario.getIdUsuario() != null)
            return false;
        if (getNome() != null ? !getNome().equals(usuario.getNome()) : usuario.getNome() != null) return false;
        if (getEmail() != null ? !getEmail().equals(usuario.getEmail()) : usuario.getEmail() != null) return false;
        return getSenha() != null ? getSenha().equals(usuario.getSenha()) : usuario.getSenha() == null;

    }

    @Override
    public int hashCode() {
        int result = getIdUsuario() != null ? getIdUsuario().hashCode() : 0;
        result = 31 * result + (getNome() != null ? getNome().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getSenha() != null ? getSenha().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
