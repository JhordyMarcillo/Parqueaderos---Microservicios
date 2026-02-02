package ec.edu.espe.ms_clientes.dto.request;

import ec.edu.espe.ms_clientes.model.AutoType;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
public class VehiculoRequestDto {

    @NotBlank(message = "La placa es obligatoria")
    @Pattern(regexp = "^[A-Z]{3}-\\d{3,4}$", message = "La placa debe tener formato AAA-123 o AAA-1234")
    private String placa;

    @NotBlank(message = "La marca es obligatoria")
    @Size(min = 2, max = 50, message = "La marca debe tener entre 2 y 50 caracteres")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    @Size(min = 2, max = 50, message = "El modelo debe tener entre 2 y 50 caracteres")
    private String modelo;

    @NotBlank(message = "El color es obligatorio")
    private String color;

    @NotNull(message = "El cilindraje es obligatorio")
    @Max(value = 10000, message = "El cilindraje incorrecto máx 10000")
    private Integer cilindraje;

    @NotBlank(message = "El año de fabricación es obligatorio")
    @Pattern(regexp = "^(19|20)\\d{2}$", message = "El año debe ser válido (ej: 1999, 2024)")
    private String anioFabricacion;

    @NotBlank(message = "La identificación del propietario es obligatoria")
    @Size(min = 10, max = 13, message = "La identificación debe tener 10 (Cédula) o 13 (RUC) dígitos")
    @Pattern(regexp = "\\d+", message = "La identificación solo debe contener números")
    private String identificacionPropietario;

    @NotNull(message = "El tipo de auto es obligatorio")
    private AutoType tipoAuto;

    @NotBlank(message = "El tipo de combustible es obligatorio")
    private String tipoCombustible;

    @NotNull(message = "El número de puertas es obligatorio")
    @Min(value = 2, message = "Mínimo 2 puertas")
    @Max(value = 5, message = "Máximo 5 puertas")
    private Integer numeroPuertas;

    @NotNull(message = "La capacidad del maletero es obligatoria")
    @Positive(message = "La capacidad debe ser un número positivo")
    private Double capacidadMaletero;

    @NotNull(message = "La capacidad de ocupantes es obligatoria")
    @Min(value = 1, message = "Mínimo 1 ocupante")
    @Max(value = 10, message = "Máximo 10 ocupantes")
    private Integer capacidadOcupantes;

    @NotBlank(message = "La transmisión es obligatoria")
    private String transmision;
}
