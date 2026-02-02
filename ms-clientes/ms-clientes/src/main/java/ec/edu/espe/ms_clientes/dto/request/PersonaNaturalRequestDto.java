package ec.edu.espe.ms_clientes.dto.request;

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

public class PersonaNaturalRequestDto extends PersonaRequestDto{
    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 30, message = "El apellido debe tener entre 2 y 30 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El apellido solo debe contener letras")
    private String apellido;

    @NotBlank(message = "El género es obligatorio")
    @Pattern(regexp = "[MFO]", message = "El género debe ser M, F u O (Mayúsculas)")
    private String genero;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
    private LocalDate fechaNacimiento;
}



