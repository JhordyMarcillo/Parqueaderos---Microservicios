package ec.edu.espe.ms_clientes.repository;

import ec.edu.espe.ms_clientes.model.Persona;
import ec.edu.espe.ms_clientes.model.PersonaJuridica;
import ec.edu.espe.ms_clientes.model.PersonaNatural;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//repository se ayudan a servicios
@Repository
public interface PersonaRepository extends JpaRepository<Persona, UUID> {

    Optional<Persona> findByIdentificacion(String identificacion);

    boolean existsByIdentificacion(String identificacion);

    @Query("SELECT pn FROM PersonaNatural pn WHERE pn.activo = true")
    List<PersonaNatural> findAllNaturalPersona();

    @Query("SELECT pj FROM PersonaJuridica pj WHERE pj.activo = true")
    List<PersonaJuridica> findAllJuridicaPersona();

    @Query("SELECT p FROM Persona p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :name,'%') )")
    List<Persona> findAllByNameIgnoreCaseContaining(@Param("name") String name);

}
