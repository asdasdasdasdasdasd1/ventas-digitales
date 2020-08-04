package pe.com.empresa.repository;


import org.springframework.data.repository.CrudRepository;
import pe.com.empresa.model.entity.Persona;

public interface PersonaRepository extends CrudRepository<Persona, Integer> {
}
