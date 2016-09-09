package api.autotam.service;


import api.autotam.model.Usuario;

/**
 * Created by Danilo on 9/9/2016.
 */


public interface EmailService {

    void sendEmail(String to, String subject, String text);

    void recoverPassword(Usuario usuario);

    void registrationConfirm(Usuario usuario);

}
