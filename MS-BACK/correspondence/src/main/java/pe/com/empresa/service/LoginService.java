package pe.com.empresa.service;

import pe.com.empresa.model.entity.Persona;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface LoginService {

    public Mono<Optional<Persona>> findUsernameById(int id);

    public Mono<Optional<Persona>> findUserByUsernameOrPassword(Optional<String> username, Optional<String> clave);
}
