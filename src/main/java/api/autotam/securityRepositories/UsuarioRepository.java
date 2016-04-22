package api.autotam.securityRepositories;

import api.autotam.model.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Danilo on 4/22/2016.
 */
public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    public Usuario findByEmail(String email);
}
