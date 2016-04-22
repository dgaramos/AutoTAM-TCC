package api.autotam.model;



import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by Danilo on 4/17/2016.
 */

@Entity
@Table(name= "USUARIO")
public class Usuario implements Serializable {


    private static final long serialVersionUID = -3756253191774836358L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer idUsuario;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL", unique=true)
    private String email;

    @Column(name = "SENHA")
    private String senha;

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

        if (!getIdUsuario().equals(usuario.getIdUsuario())) return false;
        if (!getNome().equals(usuario.getNome())) return false;
        if (!getEmail().equals(usuario.getEmail())) return false;
        return getSenha().equals(usuario.getSenha());

    }

    @Override
    public int hashCode() {
        int result = getIdUsuario().hashCode();
        result = 31 * result + getNome().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getSenha().hashCode();
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
