package ec.edu.espe.ms_clientes.service;

import ec.edu.espe.ms_clientes.dto.mapper.VehiculoMapper;
import ec.edu.espe.ms_clientes.dto.request.MotoRequestDto;
import ec.edu.espe.ms_clientes.dto.request.VehiculoRequestDto;
import ec.edu.espe.ms_clientes.dto.response.VehiculoResponseDto;
import ec.edu.espe.ms_clientes.model.Persona;
import ec.edu.espe.ms_clientes.model.Vehiculo;

import java.util.List;
import java.util.UUID;

public interface VehiculoService {

    VehiculoResponseDto getVehiculoById(UUID id);
    VehiculoResponseDto getVehiculoByPlaca(String placa);

    VehiculoResponseDto createVehiculoFamiliar(VehiculoRequestDto vehiculoRequestDto);
    VehiculoResponseDto updateVehiculoFamiliar(UUID id, VehiculoRequestDto vehiculoRequestDto);
    void deleteVehiculoFamiliar(UUID id);

    VehiculoResponseDto createVehiculoMoto(MotoRequestDto dto);

    VehiculoResponseDto updateVehiculoMoto(UUID id, MotoRequestDto vehiculoRequestDto);
    void deleteVehiculoMoto(UUID id);

    List<VehiculoResponseDto> getVehiculos();
    //VehiculoResponseDto

    List<VehiculoResponseDto> getVehiculosByPersona(String identificacion);
}
