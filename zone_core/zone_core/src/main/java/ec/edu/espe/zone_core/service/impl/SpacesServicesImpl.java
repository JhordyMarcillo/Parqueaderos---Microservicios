package ec.edu.espe.zone_core.service.impl;

import ec.edu.espe.zone_core.dto.SpaceResponseDto;
import ec.edu.espe.zone_core.dto.SpacesRequestDto;
import ec.edu.espe.zone_core.messaging.NotificactionProducer;
import ec.edu.espe.zone_core.model.SpaceStatus;
import ec.edu.espe.zone_core.model.Spaces;
import ec.edu.espe.zone_core.model.Zone;
import ec.edu.espe.zone_core.repository.SpacesRepository;
import ec.edu.espe.zone_core.repository.ZoneRepository;
import ec.edu.espe.zone_core.service.SpacesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpacesServicesImpl implements SpacesService {

    @Autowired
    private NotificactionProducer notificactionProducer;

    //inyeccion de dependencias con autowired
    @Autowired
    private SpacesRepository spacesRepository;
    private final ZoneRepository zoneRepository;

    @Override
    public SpaceResponseDto createSpace(SpacesRequestDto dto) {

        Zone zone = zoneRepository.findById(dto.getIdZone())
                .orElseThrow(() -> new RuntimeException("Zone not found"));

        Spaces objSpace =   Spaces.builder()
                .code(dto.getCodigo())
                .status(dto.getStatus())
                .isReserved(dto.isReserved())
                .priority(dto.getPriority())
                .zone(zone)
                .build();

        Spaces savedSpaces = spacesRepository.save(objSpace);
        notificactionProducer.sendSpaceCreated(savedSpaces.getId(), savedSpaces.getCode());
        return convertToDto(savedSpaces);
    }

    @Override
    public SpaceResponseDto updateSpace(UUID id, SpacesRequestDto dto) {

        Zone zone = zoneRepository.findById(dto.getIdZone())
                .orElseThrow(() -> new EntityNotFoundException("Zona no encontrada con id"));


        Spaces objSpaces = spacesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Espacio no encontrado"));

        objSpaces.setCode(dto.getCodigo());
        objSpaces.setStatus(dto.getStatus());
        objSpaces.setPriority(dto.getPriority());
        objSpaces.setZone(zone);

        Spaces updated = spacesRepository.save(objSpaces);
        notificactionProducer.sendSpaceUpdated(updated.getId(), updated.getCode());
        return convertToDto(updated);
    }

    @Override
    public void deleteSpace(UUID id) {
        Spaces objSpaces = spacesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Space not found"));

        spacesRepository.delete(objSpaces);
        notificactionProducer.sendSpaceDeleted(objSpaces.getId(), objSpaces.getCode());
    }

    @Override
    public List<SpaceResponseDto> getAllSpaces() {
        /*spacesRepository.findAll().stream()
                .map (spaces -> convertToDto(spaces))
                .collect(Collectors.toList());*/

        //devuelve un listado, stream permite interacturar un arreglo como se pueda un arreglo
        return spacesRepository.findAll().stream()
                //actua en cada uno de los objetos realizado la operacion del convert
                .map(this::convertToDto)
                //recolecta en un listado
                .collect(Collectors.toList());
    }


    @Override
    public SpaceResponseDto getSpaceById(UUID id) {

        Spaces objSpaces = spacesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Space not found"));

        return convertToDto(objSpaces);
    }

    @Override
    public SpaceResponseDto getSpacesByZone(UUID zoneId) {
        List<Spaces> spaces = spacesRepository.findByZoneId(zoneId);

        if(spaces.isEmpty()){
            throw new EntityNotFoundException("Zona no encontrada");
        }

        Spaces objSpaces = spaces.get(0);
        return convertToDto(objSpaces);
    }

    @Override
    public List<SpaceResponseDto> getAvailableSpaces() {
        return spacesRepository.findAll()
                .stream()
                .filter(spaces -> spaces.getStatus() == SpaceStatus.AVAILABLE)
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public SpaceResponseDto updateSpaceStatus(UUID id, String status) {
       Spaces space = spacesRepository.findById(id)
               .orElseThrow(() -> new EntityNotFoundException("Space not found"));

       SpaceStatus newStatus;
       try {
           newStatus = SpaceStatus.valueOf(status.toUpperCase());
       } catch (IllegalArgumentException e) {
           throw new RuntimeException("Status invalido");
       }

       space.setStatus(newStatus);
       Spaces objSpace = spacesRepository.save(space);
       return convertToDto(objSpace);
    }


    private SpaceResponseDto convertToDto(Spaces objSpace){
        return SpaceResponseDto.builder()
                .id(objSpace.getId())
                .codigo(objSpace.getCode())
                .status(objSpace.getStatus())
                .isReserved(objSpace.isReserved())
                .priority(objSpace.getPriority())
                .idZone(objSpace.getZone().getId())
                .zoneName(objSpace.getZone().getName())
                .build();
    }
}
