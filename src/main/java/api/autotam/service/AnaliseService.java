package api.autotam.service;

import api.autotam.model.Permissao;
import api.autotam.model.Usuario;

import java.util.List;

/**
 * Created by Danilo on 9/12/2016.
 */
public interface AnaliseService {

    List<Permissao> findAllAnalises(int id);

}
