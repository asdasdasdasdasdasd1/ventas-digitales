package pe.com.empresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.com.empresa.model.entity.Persona;
import pe.com.empresa.service.LoginService;
import pe.com.empresa.service.NotificationService;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;
import javax.swing.text.html.Option;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Validated
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    NotificationService notificationService;

    @GetMapping("/clientes")
    public Mono<Optional<Persona>> findUsernameById(
            @RequestParam(name = "id", required = true, defaultValue = "0")
            @Min(value = 0, message = "Minimo 0")
            @Max(value = 3, message = "Maximo 3") int id) {
        return loginService.findUsernameById(id);
    }

    @GetMapping("/cliente")
    public Mono<Optional<Persona>> findUserByUsernameOrPassword(
            @RequestParam(name="username") Optional<String> username,
            @RequestParam(name="clave") Optional<String> clave) {
        return loginService.findUserByUsernameOrPassword(username,clave);
    }

    @PostMapping("/upload")
    public Mono<String> uploadFile(@RequestParam("archivo") MultipartFile archivo){
        return notificationService.sendEmail(archivo);
    }

    @PostMapping("/uploadMultipleFiles")
    public Mono<String> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return notificationService.sendEmails(files);
    }
}
