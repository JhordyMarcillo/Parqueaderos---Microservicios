package ec.edu.espe.ms_clientes.dto.response;

import ec.edu.espe.ms_clientes.model.AutoType;
import ec.edu.espe.ms_clientes.model.MotoType;
import com.fasterxml.jackson.annotation.JsonInclude;
import ec.edu.espe.ms_clientes.model.Persona;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehiculoResponseDto {
    private UUID id;
    private Integer cilindraje;
    private String placa;
    private String marca;
    private String color;
    private String modelo;
    private String anioFabricacion;
    private Persona persona;
    private boolean activo;
    private LocalDateTime fechaCreacion;

    private AutoType tipoAuto;
    private String tipoCombustible;
    private Integer numeroPuertas;
    private Double capacidadMaleteroLitros;
    private Integer capacidadOcupantes;
    private String transmision;

    private MotoType tipo;
    private Boolean tieneCasco;
}
