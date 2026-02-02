package ec.edu.espe.ms_clientes.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class PersonaResponseDto {
    private UUID id;
    private String nombre; //nombre completo pn - razón
    private String identificacion;
    private String tipoPersona;
    private String email;
    private String telefono;
    private String direccion;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
}
