package api.autotam.restControllers;

import api.autotam.model.Permissao;
import api.autotam.services.interfaces.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Classe responsável pelo mapeamento das URIs das requisições HTTP referentes a Classe Permissão.
 *
 * @uri /permissao
 * @author Danilo
 */

@RestController
@RequestMapping("/permissao")
public class PermissaoController {

    @Autowired
    private PermissaoService permissaoService;

    /**
     * Método responsável por dar a resposta a requisição HTTP/GET referente a listagem de todas as Permissões
     * vinculadas ao Usuário em sessão.
     *
     * @uri /permissao
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Permissao>> listAllPermissoesFromUsuario() {
        System.out.println("Buscando todas as Permissões vinculadas ao Usuário Logado.");

        List<Permissao> permissoes = permissaoService.findAllPermissoesFromUsuarioLogado();

        if(permissoes.isEmpty()){
            return new ResponseEntity<List<Permissao>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Permissao>>(permissoes, HttpStatus.OK);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/GET referente as Permissões vinculadas a uma determinada
     * Análise utilizando seu id como parametro na URI
     *
     * @uri /permissoes/{id}
     * @param idAnalise
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Permissao>> listAllPermissoesFromAnalise(@PathVariable("id") Integer idAnalise) {
        System.out.println("Buscando todas as Permissões vinculadas a Análise com id " + idAnalise);

        List<Permissao> permissoes = permissaoService.findAllPermissoesFromAnalise(idAnalise);

        if(permissoes.isEmpty()){
            return new ResponseEntity<List<Permissao>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Permissao>>(permissoes, HttpStatus.OK);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/POST referente a criação de novas Permissões vinculadas a
     * uma determinada Análise
     *
     * @uri /permissao
     * @param permissao
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createPermissao(@RequestBody Permissao permissao, UriComponentsBuilder ucBuilder) {
        System.out.println("Criando Permissao vinculada a Analise "+ permissao.getAnalise().getNome()+
                " para o Usuario com email " + permissao.getUsuario().getEmail());

        permissaoService.savePermissao(permissao);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("{id}").buildAndExpand(permissao.getIdPermissao()).toUri());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/PUT referente a atualização de uma determinada Permissão
     * em relação ao seu tipo (Observador, Administrador, Testador) utilizando seu id como parametro na URI
     *
     * @uri /permissao/{id}
     * @param idPermissao
     * @param permissao
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Permissao> updatePermissao(@PathVariable("id") Integer idPermissao, @RequestBody Permissao permissao) {
        System.out.println("Atualizando Permissao " + idPermissao + "com status de Administrador igual a "
                + permissao.isAdministrador() + "e status de Testador igual a " + permissao.isTestador());

        Permissao currentPermissao = permissaoService.findById(idPermissao);

        if (currentPermissao==null) {
            System.out.println("Permissao com id " + idPermissao + " não foi encontrada");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentPermissao.setTestador(permissao.isTestador());
        currentPermissao.setAdministrador(permissao.isAdministrador());

        permissaoService.updatePermissao(currentPermissao);

        return new ResponseEntity<>(currentPermissao, HttpStatus.OK);
    }

    /**
     * Método responsável por dar a resposta a requisição HTTP/DELETE referente a remoção de uma determinada Permissão
     * existente utilizando seu id como parâmetro na URI
     *
     * @uri /permissao/{id}
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Permissao> deletePermissao(@PathVariable("id") Integer id) {
        System.out.println("Buscando e removendo Permissao com id " + id);

        permissaoService.deletePermissao(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
