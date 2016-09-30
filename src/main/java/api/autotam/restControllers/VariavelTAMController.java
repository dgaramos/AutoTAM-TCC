package api.autotam.restControllers;

import api.autotam.model.VariavelTAM;
import api.autotam.services.interfaces.VariavelTAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Danilo on 9/29/2016.
 */

@RestController
@RequestMapping("/variavelTAM")
public class VariavelTAMController {

    @Autowired
    private VariavelTAMService variavelTAMService;

    //------------------- Update a Variavel from an Analise --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<VariavelTAM> updateVariavel(@PathVariable("id") Integer idAnalise, @RequestBody VariavelTAM variavel) {
        System.out.println("Updating variavel "+ variavel.getNomeVariavel()+"from analise with id " + idAnalise);

        VariavelTAM currentVariavel = variavelTAMService.findById(variavel.getIdVariavel());

        if (currentVariavel==null) {
            System.out.println("Variavel with id " + variavel.getIdVariavel() + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentVariavel.setNomeVariavel(variavel.getNomeVariavel());

        variavelTAMService.updateVariavel(currentVariavel);

        return new ResponseEntity<VariavelTAM>(currentVariavel, HttpStatus.OK);
    }

    //-------------------Retrieve All Variaveis from Analise--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VariavelTAM>> listAllVariaveis(@PathVariable("id") Integer idAnalise) {
        List<VariavelTAM> variaveis = variavelTAMService.findAllVariaveisFromAnalise(idAnalise);
        if(variaveis.isEmpty()){
            return new ResponseEntity<List<VariavelTAM>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<VariavelTAM>>(variaveis, HttpStatus.OK);
    }

    //------------------- Delete a Variavel --------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<VariavelTAM> deleteVariavel(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting Variavel with id " + id);

        variavelTAMService.deleteVariavel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
