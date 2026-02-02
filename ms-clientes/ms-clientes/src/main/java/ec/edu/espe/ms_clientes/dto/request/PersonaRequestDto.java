package ec.edu.espe.ms_clientes.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
public class PersonaRequestDto {

    @NotBlank(message = "El campo identificación no puede estar vacío")
    @Size(min = 10, max = 13, message = "La identificación debe tener entre 10 (Cédula) y 13 (RUC) caracteres")
    @Pattern(regexp = "\\d+", message = "La identificación debe contener solo números")
    private String identificacion;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre solo puede contener letras")
    private String nombre;


    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del correo electrónico no es válido")
    @Size(max = 100, message = "El email no puede exceder los 100 caracteres")
    private String email;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 100, message = "La dirección no puede exceder los 100 caracteres")
    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 8, max = 10, message = "El teléfono debe tener entre 8 y 10 dígitos")
    @Pattern(regexp = "[0-9+\\-]+", message = "El teléfono contiene caracteres inválidos")
    private String telefono;
}
