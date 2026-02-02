package ec.edu.espe.ms_clientes.service.impl;

import ec.edu.espe.ms_clientes.dto.mapper.PersonaMapper;
import ec.edu.espe.ms_clientes.dto.request.PersonaJuridicaRequestDto;
import ec.edu.espe.ms_clientes.dto.request.PersonaNaturalRequestDto;
import ec.edu.espe.ms_clientes.dto.response.PersonaResponseDto;
import ec.edu.espe.ms_clientes.messaging.NotificactionProducer;
import ec.edu.espe.ms_clientes.model.Persona;
import ec.edu.espe.ms_clientes.model.PersonaJuridica;
import ec.edu.espe.ms_clientes.model.PersonaNatural;
import ec.edu.espe.ms_clientes.repository.PersonaRepository;
import ec.edu.espe.ms_clientes.service.PersonaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PersonaServiceImpl implements PersonaService {

    private final NotificactionProducer notificactionProducer;
    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;

    @Override
    @Transactional
    public PersonaResponseDto createPersonaNatural(PersonaNaturalRequestDto dto) {
        if (personaRepository.existsByIdentificacion(dto.getIdentificacion())) {
            log.error("Identificación ya existe: {}", dto.getIdentificacion());
            throw new RuntimeException("Ya existe una persona con la identificacion " + dto.getIdentificacion());
        }

        PersonaNatural personaNatural = personaMapper.toEntity(dto);

        if (!personaNatural.validarIdentificacion()) {
            throw new RuntimeException("Cédula incorrecta");
        }

        Persona guardado = personaRepository.save(personaNatural);
        notificactionProducer.sendPersonaCreated(guardado.getId(), guardado.getIdentificacion(), "NATURAL");
        log.info("Persona Natural guardada con id {}", guardado.getId());
        return personaMapper.toDto(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonaResponseDto findPersonaById(String identificacion) {
        Persona persona = personaRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada"));

        return personaMapper.toDto(persona);
    }

    @Override
    @Transactional
    public PersonaResponseDto createPersonaJuridica(PersonaJuridicaRequestDto dto) {
        if (personaRepository.existsByIdentificacion(dto.getIdentificacion())) {
            log.error("RUC ya existe: {}", dto.getIdentificacion());
            throw new RuntimeException("Ya existe una persona jurídica con el RUC " + dto.getIdentificacion());
        }

        PersonaJuridica personaJuridica = personaMapper.toEntity(dto);

        if (!personaJuridica.validarIdentificacion()) {
            throw new RuntimeException("RUC incorrecto");
        }

        Persona guardado = personaRepository.save(personaJuridica);
        notificactionProducer.sendPersonaCreated(guardado.getId(), guardado.getIdentificacion(), "JURIDICA");
        log.info("Persona Jurídica guardada con id {}", guardado.getId());
        return personaMapper.toDto(guardado);
    }

    @Override
    @Transactional
    public PersonaResponseDto updatePersonaNatural(PersonaNaturalRequestDto dto, UUID id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con id: " + id));

        if (!(persona instanceof PersonaNatural)) {
            throw new RuntimeException("El ID corresponde a una Persona Jurídica, no se puede actualizar como Natural");
        }

        PersonaNatural natural = (PersonaNatural) persona;

        natural.setNombre(dto.getNombre());
        natural.setApellido(dto.getApellido());
        natural.setDireccion(dto.getDireccion());
        natural.setTelefono(dto.getTelefono());
        natural.setEmail(dto.getEmail());

        Persona actualizado = personaRepository.save(natural);
        notificactionProducer.sendPersonaUpdated(actualizado.getId(), actualizado.getIdentificacion());
        log.info("Persona Natural actualizado con id {}", actualizado.getId());
        return personaMapper.toDto(actualizado);
    }

    @Override
    @Transactional
    public PersonaResponseDto updatePersonaJuridica(PersonaJuridicaRequestDto dto, UUID id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con id: " + id));

        if (!(persona instanceof PersonaJuridica)) {
            throw new RuntimeException("El ID corresponde a una Persona Natural, no se puede actualizar como Jurídica");
        }

        PersonaJuridica juridica = (PersonaJuridica) persona;

        juridica.setRazonSocial(dto.getRazonSocial());
        juridica.setDireccion(dto.getDireccion());
        juridica.setTelefono(dto.getTelefono());
        juridica.setEmail(dto.getEmail());

        Persona actualizado = personaRepository.save(juridica);
        notificactionProducer.sendPersonaUpdated(actualizado.getId(), actualizado.getIdentificacion());
        log.info("Persona Juridica actualizado con id {}", actualizado.getId());
        return personaMapper.toDto(actualizado);
    }

    @Override
    @Transactional
    public void deletePersona(UUID id) {
        if (!personaRepository.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar. Persona no encontrada con id: " + id);
        }
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con id: " + id));

        personaRepository.delete(persona); // Borras

        notificactionProducer.sendPersonaDeleted(persona.getId(), persona.getIdentificacion());
        log.info("Persona eliminada con id {}", id);
    }

    @Override
    public List<PersonaResponseDto> findAllPersona() {
        return personaRepository.findAll().stream()
                .map(personaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonaNatural> findAllNaturalPersona() {
        return personaRepository.findAll().stream()
                .filter(p -> p instanceof PersonaNatural)
                .map(p -> (PersonaNatural) p)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonaJuridica> findAllJuridicaPersona() {
        return personaRepository.findAll().stream()
                .filter(p -> p instanceof PersonaJuridica)
                .map(p -> (PersonaJuridica) p)
                .collect(Collectors.toList());
    }

    @Override
    public List<Persona> findAllByNameIgnoreCaseContaining(String name) {
        return personaRepository.findAllByNameIgnoreCaseContaining(name);
    }
}