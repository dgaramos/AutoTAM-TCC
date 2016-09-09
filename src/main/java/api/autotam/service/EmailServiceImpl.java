package api.autotam.service;

import api.autotam.config.EmailConfig;
import api.autotam.dao.UsuarioDAO;
import api.autotam.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by Danilo on 9/9/2016.
 */

@Service("emailService")
@Transactional
public class EmailServiceImpl implements EmailService{


    public void sendEmail(String to, String subject, String text){

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

    public void recoverPassword(Usuario usuario) {

        String emailContent =
                "<p>As informações da sua conta no AutoTAM estão impressas no final desta mensagem.</p>" +
                        "<p>Esta senha permite o acesso a o sistema e a todas as análises as quais sua conta for vinculada,</p>" +
                        "<p>não compartilhe com terceiros, essa senha é pessoal e intransferível!</p>"+
                        "<br>"+
                        "<p>Informações do usuário</p>" +
                        "<p>Seu usuario é: " + usuario.getEmail() + "</p>" +
                        "<p>Sua senha é: " + usuario.getSenha() + "</p>" +
                        "<br>" +
                        "<p>Atenciosamente,</p>" +
                        "<p>Danilo Gonçalves Alves Ramos, Desenvolvedor do AutoTAM</p>";
        sendEmail(usuario.getEmail(),"[AutoTAM] - Recuperação de Senha", emailContent);
    }

    public void registrationConfirm(Usuario usuario) {

        String emailContent =
                "<p>Olá "+usuario.getNome()+"!</p>"+
                        "<p>Seja bem vindo ao AutoTAM, um automador para análises TAM</p>"+
                        "As informações da sua conta no AutoTAM estão impressas no final desta mensagem.</p>" +
                        "<p>Esta senha permite o acesso a o sistema e a todas as análises as quais sua conta for vinculada,</p>" +
                        "<p>não compartilhe com terceiros, essa senha é pessoal e intransferível!</p>"+
                        "<br>"+
                        "<p>Informações do usuário</p>" +
                        "<p>Seu usuario é: " + usuario.getEmail() + "</p>" +
                        "<p>Sua senha é: " + usuario.getSenha() + "</p>" +
                        "<br>" +
                        "<p>Atenciosamente,</p>" +
                        "<p>Danilo Gonçalves Alves Ramos, Desenvolvedor do AutoTAM</p>";
        sendEmail(usuario.getEmail(),"[AutoTAM] - Bem Vindo ao AutoTAM", emailContent);
    }

}
