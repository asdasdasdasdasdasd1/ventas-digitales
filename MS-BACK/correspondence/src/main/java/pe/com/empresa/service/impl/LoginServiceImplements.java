package pe.com.empresa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.empresa.model.entity.Persona;
import pe.com.empresa.util.Constants;
import pe.com.empresa.util.ExcetionAndError;
import reactor.core.publisher.Mono;
import pe.com.empresa.repository.PersonaRepository;
import pe.com.empresa.service.LoginService;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class LoginServiceImplements implements LoginService {

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public Mono<Optional<Persona>> findUsernameById(final int id) {
        return Mono.fromFuture( CompletableFuture.supplyAsync(() ->
            personaRepository.findById(id)
        ));
    }

    @Override
    public Mono<Optional<Persona>> findUserByUsernameOrPassword(final Optional<String> username, final Optional<String> clave) {
        if ( username.isPresent() && clave.isPresent() ) {
            return Mono.just(Optional.of(new Persona()));
        }
        return null;
    }

    public void validateError(final String username, final int type) {
        String msg_success = ExcetionAndError.MSG_SUCCESS.getDescription();
    }

    public void validateAndShowError(final String username, final String clave) {
        String msg_error = ExcetionAndError.MSG_ERROR.getDescription();
    }
}
