package ec.edu.espe.ms_clientes.dto.mapper;

import ec.edu.espe.ms_clientes.dto.request.PersonaJuridicaRequestDto;
import ec.edu.espe.ms_clientes.dto.request.PersonaNaturalRequestDto;
import ec.edu.espe.ms_clientes.dto.response.PersonaResponseDto;
import ec.edu.espe.ms_clientes.model.*;
import lombok.Builder;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class PersonaMapper {

    //natural
    public PersonaNatural toEntity(PersonaNaturalRequestDto dto) {

        return PersonaNatural.builder()
                .identificacion(dto.getIdentificacion())
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .direccion(dto.getDireccion())
                .activo(true)
                .apellido(dto.getApellido())
                .fechaNacimiento(dto.getFechaNacimiento())
                .genero(mapGenero(dto.getGenero()))
                .build();
    }

    //juridica
    public PersonaJuridica toEntity(PersonaJuridicaRequestDto dto) {

        return PersonaJuridica.builder()

                .identificacion(dto.getIdentificacion())
                .nombre(dto.getRazonSocial())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .direccion(dto.getDireccion())
                .activo(true)
                .razonSocial(dto.getRazonSocial())
                .representanteLegal(dto.getRepresentanteLegal())
                .fechaConstitucion(dto.getFechaConstitucion())
                .actividadEconomica(mapActividad(dto.getActividadEconomica()))
                .build();
    }


    //response
    public PersonaResponseDto toDto(Persona persona) {

        return PersonaResponseDto.builder()
                .id(persona.getId())
                .identificacion(persona.getIdentificacion())
                .nombre(persona.getNombre())
                .email(persona.getEmail())
                .telefono(persona.getTelefono())
                .direccion(persona.getDireccion())
                .tipoPersona(determinarTipo(persona))
                .activo(persona.getActivo())
                .build();
    }

    private String determinarTipo(Persona p){
        if(p instanceof PersonaNatural) {
            return "NATURAL";
        } else if(p instanceof PersonaJuridica) {
            return "JURIDICA";
        } else {
            return "DESCONOCIDO";
        }
    }

    private GeneroType mapGenero(String generoStr) {
        if (generoStr == null || generoStr.trim().isEmpty()) {
            return null;
        }
        try {
            return GeneroType.valueOf(generoStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private ActividadType mapActividad(String actividadStr) {
        if (actividadStr == null || actividadStr.trim().isEmpty()) {
            return null;
        }
        try {
            return ActividadType.valueOf(actividadStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}