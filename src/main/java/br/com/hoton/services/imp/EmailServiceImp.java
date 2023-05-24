package br.com.hoton.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp {

    @Autowired
    private JavaMailSender emailSender;

    public void enviarEmailSimples(
      String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("hoton@hoton.com.br");
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
        
        SimpleMailMessage message2 = new SimpleMailMessage(); 
        message2.setFrom("hoton@hoton.com.br");
        message2.setTo("victor.rafalucca@gmail.com"); 
        message2.setSubject(subject); 
        message2.setText(text);
        emailSender.send(message2);
    }

}
