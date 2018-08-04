package api.autotam.services.implementations;

import api.autotam.configs.EmailConfig;
import api.autotam.model.Usuario;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;

/**
 * Classe de serviço responsável por enviar os e-mails provindos de determinadas operações da aplicação.
 *
 * @author Danilo
 */

@Service("emailService")
@Transactional
public class EmailService{

    /**
     * Método responsável pela operação de envio de emails da aplicação.
     *
     * @param to
     * @param subject
     * @param text
     */
    private void sendEmail(String to, String subject, String text){

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(EmailConfig.class);
        ctx.refresh();
        JavaMailSenderImpl mailSender = ctx.getBean(JavaMailSenderImpl.class);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mailMsg = new MimeMessageHelper(mimeMessage);

        try {
            mailMsg.setFrom(new InternetAddress("autotamtcc@gmail.com", "AutoTAM"));
            mailMsg.setTo(to);
            mailMsg.setSubject(subject);
            mailMsg.setText(text, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        mailSender.send(mimeMessage);
        System.out.println("---Done---");
    }

    /**
     * Método responsável por guardar o conteúdo do email de recuperação de senha do Usuário.
     *
     * @param usuario
     */
    public void recoverPassword(Usuario usuario, String newPassword) {
        String emailContent =
                "<p>Olá "+usuario.getNome()+"!</p>"+
                        "<p>Foi informado ao sistema que você teria esquecido a sua senha.</p>" +
                        "<p>As informações da sua conta no AutoTAM estão impressas no final desta mensagem.</p>" +
                        "<p>Esta senha permite o acesso a o sistema e a todas as análises as quais sua conta for vinculada,</p>" +
                        "<p>não é recomendado que sua senha seja compartilhada com terceiros, essa senha é pessoal e intransferível!</p>"+
                        "<br>"+
                        "<p>Informações do usuário</p>" +
                        "<p>Seu email é: " + usuario.getEmail() + "</p>" +
                        "<p>Sua senha nova é: " + newPassword + "</p>" +
                        "<br>" +
                        "<p>Atenciosamente,</p>" +
                        "<p>Danilo Gonçalves Alves Ramos, Desenvolvedor do AutoTAM</p>";
        sendEmail(usuario.getEmail(),"[AutoTAM] - Recuperação de Senha", emailContent);
    }

    /**
     * Método responsável por guardar o conteúdo do email de confirmação de registro de Usuário.
     *
     * @param usuario
     */
    public void registrationConfirm(Usuario usuario) {
        String emailContent =
                "<p>Olá "+usuario.getNome()+"!</p>"+
                        "<p>Seja bem vindo ao AutoTAM, um automador para análises TAM</p>"+
                        "<p>As informações da sua conta no AutoTAM estão impressas no final desta mensagem.</p>" +
                        "<p>Esta senha permite o acesso a o sistema e a todas as análises as quais sua conta for vinculada,</p>" +
                        "<p>não é recomendado que sua senha seja compartilhada com terceiros, essa senha é pessoal e intransferível!</p>"+
                        "<br>"+
                        "<p>Informações do usuário</p>" +
                        "<p>Seu nome de usuario é: "+usuario.getNome()+"</p>"+
                        "<p>Seu email é: " + usuario.getEmail() + "</p>" +
                        "<p>Sua senha é: " + usuario.getSenha() + "</p>" +
                        "<br>" +
                        "<p>Atenciosamente,</p>" +
                        "<p>Danilo Gonçalves Alves Ramos, Desenvolvedor do AutoTAM</p>";
        sendEmail(usuario.getEmail(),"[AutoTAM] - Bem Vindo ao AutoTAM", emailContent);
    }

    /**
     * Método responsável por guardar o conteúdo do email com as atualizações de registro de Usuário.
     *
     * @param usuario
     */
    public void updateProfile(Usuario usuario) {
        String emailContent =
                "<p>Olá "+usuario.getNome()+"!</p>"+
                        "<p>Estamos encaminhando esse email para que você tenha suas informações atualizadas.</p>"+
                        "<p>As informações da sua conta no AutoTAM estão impressas no final desta mensagem.</p>" +
                        "<p>Não é recomendado que sua senha seja compartilhada com terceiros, essa senha é pessoal e intransferível!</p>"+
                        "<br>"+
                        "<p>Informações do usuário</p>" +
                        "<p>Seu nome de usuario é: "+usuario.getNome()+"</p>"+
                        "<p>Seu email é: " + usuario.getEmail() + "</p>" +
                        "<p>Sua senha é: " + usuario.getSenha() + "</p>" +
                        "<br>" +
                        "<p>Atenciosamente,</p>" +
                        "<p>Danilo Gonçalves Alves Ramos, Desenvolvedor do AutoTAM</p>";
        sendEmail(usuario.getEmail(),"[AutoTAM] - Aviso de atualização de Perfil", emailContent);
    }

}
