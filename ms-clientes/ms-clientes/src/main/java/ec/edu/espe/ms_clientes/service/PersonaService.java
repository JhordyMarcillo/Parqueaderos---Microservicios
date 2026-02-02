package ec.edu.espe.ms_clientes.service;

import ec.edu.espe.ms_clientes.dto.request.PersonaJuridicaRequestDto;
import ec.edu.espe.ms_clientes.dto.request.PersonaNaturalRequestDto;
import ec.edu.espe.ms_clientes.dto.response.PersonaResponseDto;
import ec.edu.espe.ms_clientes.model.Persona;
import ec.edu.espe.ms_clientes.model.PersonaJuridica;
import ec.edu.espe.ms_clientes.model.PersonaNatural;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PersonaService {
    PersonaResponseDto createPersonaNatural(PersonaNaturalRequestDto dto);
    PersonaResponseDto createPersonaJuridica(PersonaJuridicaRequestDto dto);

    PersonaResponseDto updatePersonaNatural(PersonaNaturalRequestDto dto, UUID id);
    PersonaResponseDto updatePersonaJuridica(PersonaJuridicaRequestDto dto, UUID id);

    void deletePersona(UUID id);

    List<PersonaResponseDto> findAllPersona();

    List<PersonaNatural> findAllNaturalPersona();

    List<PersonaJuridica> findAllJuridicaPersona();

    List<Persona> findAllByNameIgnoreCaseContaining(@Param("name") String name);

    PersonaResponseDto findPersonaById(String identificacion);
}
