package ec.edu.espe.ms_clientes.dto.request;

import ec.edu.espe.ms_clientes.model.Persona;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PersonaJuridicaRequestDto extends PersonaRequestDto {

    @NotBlank(message = "La razón social es obligatoria")
    @Size(max = 100, message = "La razón social no debe exceder los 100 caracteres")
    private String razonSocial;

    @NotBlank(message = "La actividad económica es obligatoria")
    private String actividadEconomica;

    @NotBlank(message = "El representante legal es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre del representante debe tener entre 3 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s.]+$", message = "El representante legal contiene caracteres inválidos")
    private String representanteLegal;

    @NotNull(message = "La fecha de constitución es obligatoria")
    @Past(message = "La fecha de constitución debe ser anterior a la fecha actual")
    private LocalDate fechaConstitucion;
}
