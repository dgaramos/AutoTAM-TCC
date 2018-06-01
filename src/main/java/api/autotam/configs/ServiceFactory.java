package api.autotam.configs;

import api.autotam.services.implementations.*;
import api.autotam.services.interfaces.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Classe de configuração responsável pelo mapeamento dos Services da Aplicação.
 *
 * @author Danilo
 */

@Configuration
@EnableTransactionManagement
public class ServiceFactory {

    @Bean(name="usuarioService")
    public UsuarioService usuarioService(){ return new UsuarioServiceImpl();}

    @Bean (name="permissaoService")
    public PermissaoService permissaoService(){ return new PermissaoServiceImpl();}

    @Bean (name="analiseService")
    public AnaliseService analiseService(){ return new AnaliseServiceImpl();}

    @Bean (name="variavelTAMService")
    public VariavelTAMService variavelTAMService(){ return new VariavelTAMServiceImpl();}

    @Bean(name="questaoService")
    public QuestaoService questaoService(){ return new QuestaoServiceImpl();}

    @Bean(name="respostaService")
    public RespostaService respostaService(){ return new RespostaServiceImpl();}

    @Bean(name="questionarioService")
    public QuestionarioService questionarioService(){ return new QuestionarioServiceImpl();}

    @Bean(name="opcaoDeObjetoService")
    public OpcaoDeObjetoService opcaoDeObjetoService(){ return new OpcaoDeObjetoServiceImpl();}

    @Bean(name="resultadoOpcaoQuestaoService")
    public ResultadoOpcaoQuestaoService resultadoOpcaoQuestaoService(){ return new ResultadoOpcaoQuestaoServiceImpl();}

    @Bean(name="resultadoOpcaoVariavelService")
    public ResultadoOpcaoVariavelService resultadoOpcaoVariavelService(){ return new ResultadoOpcaoVariavelServiceImpl();}
}
