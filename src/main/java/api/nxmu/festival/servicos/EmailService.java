package api.nxmu.festival.servicos;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Value("${mail.remetente}")
    private String remetente;

    // Note: destinatario property is removed since it is not needed for multiple recipients

    public void sendSimpleMessage(String subject, String text, String[] destinatarios) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(remetente);
        message.setTo(destinatarios);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendMessageWithAttachment(String assunto, String html, String fileName, String pathToAttachment, String pathToAttachment2, String[] destinatarios) throws MessagingException, FileNotFoundException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(remetente);
        helper.setTo(destinatarios);
        helper.setSubject(assunto);
        helper.setText(html, true);

        if(pathToAttachment != null && !pathToAttachment.isEmpty()) {
	        FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
	        if (file.exists()) {
	        	helper.addAttachment(fileName, file);
	        }
	        
        }

        
        if(pathToAttachment2 != null && !pathToAttachment2.isEmpty()) {
            FileSystemResource file2 = new FileSystemResource(new File(pathToAttachment2));
            if (file2.exists()) {
            	helper.addAttachment(fileName, file2);
            }                    	
        }
        emailSender.send(message);

    }
}
