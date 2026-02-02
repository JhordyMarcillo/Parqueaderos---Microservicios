package ec.edu.espe.ms_clientes.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persona")
@DiscriminatorColumn(name = "tipo_persona") // Coincide con tu esquema
public abstract class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Genera el UUID desde Java
    @Column(name = "id", updatable = false, nullable = false) // <--- CAMBIO CLAVE: "id", NO "id_persona"
    private UUID id;

    @Column(nullable = false, unique = true)
    private String identificacion;

    @Column(nullable = false)
    private String nombre; // Veo en tu tabla 'persona' que el nombre está aquí

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Boolean activo;

    private LocalDateTime fechaCreacion;

    public abstract boolean validarIdentificacion();

    @PrePersist
    protected void onCreate(){
        this.fechaCreacion = LocalDateTime.now();
        this.activo = true;
    }
}
