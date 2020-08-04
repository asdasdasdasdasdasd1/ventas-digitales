package pe.com.empresa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.com.empresa.service.NotificationService;
import reactor.core.publisher.Mono;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.concurrent.CompletableFuture;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public Mono<String> sendEmail(MultipartFile multipartFile) {
        if(multipartFile.isEmpty())
            return Mono.just("vacio");
        return Mono
                .fromFuture(CompletableFuture.supplyAsync(() -> syncSendEmail(multipartFile)));
    }

    public String syncSendEmail(MultipartFile multipartFile) {
        try{
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("noreply@baeldung.com");
            helper.setTo("abromero2102@gmail.com");
            helper.setSubject("Test email");
            helper.setText("Text");
            FileSystemResource file
                    = new FileSystemResource(convertMultiPartToFile(multipartFile));
            helper.addAttachment(multipartFile.getOriginalFilename(), file);
            emailSender.send(message);
            return "si";
        } catch(Exception e){
            return e.getMessage();
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    @Override
    public Mono<String> sendEmails(MultipartFile[] multipartFile) {
        return Mono
                .fromFuture(CompletableFuture.supplyAsync(() -> syncSendEmails(multipartFile)));
    }

    public String syncSendEmails(MultipartFile[] multipartFile) {
        try{
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("noreply@baeldung.com");
            helper.setTo("abromero2102@gmail.com");
            helper.setSubject("Test email");
            helper.setText("Text");
            for (MultipartFile mpf : multipartFile) {
                FileSystemResource file
                        = new FileSystemResource(convertMultiPartToFile(mpf));
                helper.addAttachment(mpf.getOriginalFilename(), file);
            }
            emailSender.send(message);
            return "si";
        } catch(Exception e){
            return e.getMessage();
        }
    }
}
