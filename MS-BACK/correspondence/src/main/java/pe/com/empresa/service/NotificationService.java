package pe.com.empresa.service;

import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;
import java.io.IOException;

public interface NotificationService {

    Mono<String> sendEmail(MultipartFile multipartFile);

    Mono<String> sendEmails(MultipartFile[] multipartFile);
}
