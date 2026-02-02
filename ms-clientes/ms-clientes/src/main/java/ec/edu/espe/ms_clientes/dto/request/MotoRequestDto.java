package ec.edu.espe.ms_clientes.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotoRequestDto extends VehiculoRequestDto {

    @NotBlank(message = "El tipo de moto es obligatorio")
    private String tipo;

    @NotNull(message = "Debe especificar si tiene casco (true/false)")
    private Boolean tieneCasco;
}
