package ec.edu.espe.ms_clientes.dto.mapper;

import ec.edu.espe.ms_clientes.dto.request.MotoRequestDto;
import ec.edu.espe.ms_clientes.dto.request.VehiculoRequestDto;
import ec.edu.espe.ms_clientes.dto.response.VehiculoResponseDto;
import ec.edu.espe.ms_clientes.model.Moto;
import ec.edu.espe.ms_clientes.model.*;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public class VehiculoMapper {

        //moto
        public Moto toEntity(MotoRequestDto dto) {

            return Moto.builder()
                    .placa(dto.getPlaca())
                    .marca(dto.getMarca())
                    .modelo(dto.getModelo())
                    .color(dto.getColor())
                    .cilindraje(dto.getCilindraje())
                    .anioFabricacion(dto.getAnioFabricacion())
                    .activo(true)

                    .tipo(mapTipoMoto(dto.getTipo()))
                    .tieneCasco(dto.getTieneCasco())
                    .build();
        }

        //Auto
        public AutoFamiliar toEntity(VehiculoRequestDto dto) {

            return AutoFamiliar.builder()
                    .placa(dto.getPlaca())
                    .marca(dto.getMarca())
                    .modelo(dto.getModelo())
                    .color(dto.getColor())
                    .cilindraje(dto.getCilindraje())
                    .anioFabricacion(dto.getAnioFabricacion())
                    .activo(true)

                    .tipoAuto(dto.getTipoAuto())
                    .tipoCombustible(dto.getTipoCombustible())
                    .numeroPuertas(dto.getNumeroPuertas())
                    .capacidadMaleteroLitros(dto.getCapacidadMaletero())
                    .capacidadOcupantes(dto.getCapacidadOcupantes())
                    .transmision(dto.getTransmision())
                    .build();
        }

        //response
        public VehiculoResponseDto toDto(Vehiculo vehiculo) {

            return VehiculoResponseDto.builder()
                    .id(vehiculo.getId())
                    .cilindraje(vehiculo.getCilindraje())
                    .placa(vehiculo.getPlaca())
                    .marca(vehiculo.getMarca())
                    .color(vehiculo.getColor())
                    .modelo(vehiculo.getModelo())
                    .anioFabricacion(vehiculo.getAnioFabricacion())
                    .persona(vehiculo.getPersona())
                    .activo(vehiculo.getActivo())
                    .fechaCreacion(vehiculo.getFechaCreacion())
                    .build();
        }


        private MotoType mapTipoMoto(String tipoStr) {
            if (tipoStr == null || tipoStr.trim().isEmpty()) {
                return null;
            }

            try {
                return MotoType.valueOf(tipoStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                return null;
            }

        }
}
