package ec.edu.espe.ms_clientes.service.impl;

import ec.edu.espe.ms_clientes.dto.request.MotoRequestDto;
import ec.edu.espe.ms_clientes.dto.request.VehiculoRequestDto;
import ec.edu.espe.ms_clientes.dto.response.VehiculoResponseDto;
import ec.edu.espe.ms_clientes.messaging.NotificactionProducer;
import ec.edu.espe.ms_clientes.model.*;
import ec.edu.espe.ms_clientes.repository.PersonaRepository;
import ec.edu.espe.ms_clientes.repository.VehiculoRepository;
import ec.edu.espe.ms_clientes.service.VehiculoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehiculoServiceImpl implements VehiculoService {

    private final NotificactionProducer notificactionProducer;
    private final VehiculoRepository vehiculoRepository;
    private final PersonaRepository personaRepository;

    @Override
    public VehiculoResponseDto getVehiculoById(UUID id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehículo no encontrado con id: " + id));
        return mapToDto(vehiculo);
    }

    @Override
    public VehiculoResponseDto getVehiculoByPlaca(String placa) {
        Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new EntityNotFoundException("Vehiculo no encontrado"));

        return mapToDto(vehiculo);
    }

    @Override
    public List<VehiculoResponseDto> getVehiculosByPersona(String identificacion) {

        Persona persona = personaRepository
                .findByIdentificacion(identificacion)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

        return vehiculoRepository
                .findByPersona(persona)
                .stream()
                .map(v -> mapToDto(v))
                .toList();
    }

    @Override
    public VehiculoResponseDto createVehiculoFamiliar(VehiculoRequestDto dto) {

        Persona persona = personaRepository.findByIdentificacion(dto.getIdentificacionPropietario())
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada"));

        AutoFamiliar vehiculo = AutoFamiliar.builder()
                // Datos comunes (padre)
                .placa(dto.getPlaca())
                .marca(dto.getMarca())
                .modelo(dto.getModelo())
                .color(dto.getColor())
                .anioFabricacion(dto.getAnioFabricacion())
                .cilindraje(dto.getCilindraje())
                .persona(persona)

                // 🔒 Regla de negocio
                .activo(true)

                // Datos específicos (hijo)
                .tipoAuto(dto.getTipoAuto())
                .tipoCombustible(dto.getTipoCombustible())
                .numeroPuertas(dto.getNumeroPuertas())
                .capacidadMaleteroLitros(dto.getCapacidadMaletero())
                .capacidadOcupantes(dto.getCapacidadOcupantes())
                .transmision(dto.getTransmision())
                .build();

        AutoFamiliar saved = vehiculoRepository.save(vehiculo);

        notificactionProducer.sendAutoFamiliarCreated(saved.getId(), saved.getPlaca());

        return mapToDto(saved);
    }


    @Override
    public VehiculoResponseDto updateVehiculoFamiliar(UUID id, VehiculoRequestDto dto) {
        Vehiculo vehiculoEncontrado = vehiculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehículo no encontrado con id: " + id));

        if (!(vehiculoEncontrado instanceof AutoFamiliar)) {
            throw new RuntimeException("El vehículo encontrado no es un Auto Familiar");
        }
        AutoFamiliar objVehiFam = (AutoFamiliar) vehiculoEncontrado;

        objVehiFam.setColor(dto.getColor());
        objVehiFam.setTipoCombustible(dto.getTipoCombustible());

        AutoFamiliar updated = vehiculoRepository.save(objVehiFam);

        notificactionProducer.sendAutoFamiliarUpdate(updated.getId(), updated.getColor());

        return mapToDto(updated);
    }

    @Override
    public void deleteVehiculoFamiliar(UUID id) {
        Vehiculo vehFam = vehiculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehículo no encontrado con id: " + id));

        vehiculoRepository.delete(vehFam);
        notificactionProducer.sendAutoFamiliarDelete(vehFam.getId(),  vehFam.getPlaca());
    }

    @Override
    public VehiculoResponseDto createVehiculoMoto(MotoRequestDto dto) {
        Persona persona = personaRepository.findByIdentificacion(dto.getIdentificacionPropietario())
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrado"));

        Moto objMoto = Moto.builder()
                .placa(dto.getPlaca())
                .marca(dto.getMarca())
                .modelo(dto.getModelo())
                .color(dto.getColor())
                .anioFabricacion(dto.getAnioFabricacion())
                .cilindraje(dto.getCilindraje())
                .persona(persona)

                .tieneCasco(dto.getTieneCasco())
                .tipo(MotoType.valueOf(dto.getTipo()))
                .build();

        Moto savedMoto = vehiculoRepository.save(objMoto);

        notificactionProducer.sendMotoCreated(savedMoto.getId(), savedMoto.getPlaca());

        return mapToDto(savedMoto);
    }

    @Override
    public VehiculoResponseDto updateVehiculoMoto(UUID id, MotoRequestDto dto) {
        Vehiculo vehiculoEncontrado = vehiculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Moto no encontrada con id: " + id));

        if (!(vehiculoEncontrado instanceof Moto)) {
            throw new RuntimeException("El vehículo encontrado no es una Moto");
        }
        Moto objMoto = (Moto) vehiculoEncontrado;

        objMoto.setColor(dto.getColor());
        objMoto.setTipo(MotoType.valueOf(dto.getTipo()));
        objMoto.setTieneCasco(dto.getTieneCasco());

        if(dto.getCilindraje() != null) {
            objMoto.setCilindraje(dto.getCilindraje());
        }

        Moto updated = vehiculoRepository.save(objMoto);
        notificactionProducer.sendMotoUpdate(updated.getId(), updated.getColor());

        return mapToDto(updated);
    }


    @Override
    public void deleteVehiculoMoto(UUID id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehículo no encontrado con id: " + id));

        if (!(vehiculo instanceof Moto)) {
            throw new RuntimeException("El ID proporcionado no corresponde a una Moto");
        }
        vehiculoRepository.delete(vehiculo);
        notificactionProducer.sendMotoDelete(vehiculo.getId(), vehiculo.getPlaca());
    }

    @Override
    public List<VehiculoResponseDto> getVehiculos() {
        return vehiculoRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private VehiculoResponseDto mapToDto(Vehiculo vehiculo) {
        VehiculoResponseDto dto = new VehiculoResponseDto();

        dto.setId(vehiculo.getId());
        dto.setPlaca(vehiculo.getPlaca());
        dto.setMarca(vehiculo.getMarca());
        dto.setModelo(vehiculo.getModelo());
        dto.setColor(vehiculo.getColor());
        dto.setAnioFabricacion(vehiculo.getAnioFabricacion());
        dto.setCilindraje(vehiculo.getCilindraje());
        dto.setActivo(vehiculo.getActivo());
        dto.setFechaCreacion(vehiculo.getFechaCreacion());


        if (vehiculo instanceof AutoFamiliar) {
            AutoFamiliar auto = (AutoFamiliar) vehiculo;
            dto.setTipoAuto(auto.getTipoAuto());
            dto.setTipoCombustible(auto.getTipoCombustible());
            dto.setNumeroPuertas(auto.getNumeroPuertas());
            dto.setCapacidadMaleteroLitros(auto.getCapacidadMaleteroLitros());
            dto.setCapacidadOcupantes(auto.getCapacidadOcupantes());
            dto.setTransmision(auto.getTransmision());


        } else if (vehiculo instanceof Moto) {
            Moto moto = (Moto) vehiculo;
            dto.setTipo(moto.getTipo());
            dto.setTipo(moto.getTipo());
            dto.setTieneCasco(moto.getTieneCasco());

            if(moto.getTipo() != null) {
                dto.setTipo(moto.getTipo());
            }
        }

        return dto;
    }
}