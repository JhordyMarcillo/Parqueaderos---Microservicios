package ec.edu.espe.ms_clientes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "vehiculo")
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder //se puede crear objetos con atributos del padre (hereda)
@DiscriminatorColumn(name = "tipo_vehiculo", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@AllArgsConstructor
//@Builder actua solo sobre la entidad


public abstract class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // <--- AGREGA ESTA LÍNEA
    @Column(name = "id_vehiculo", updatable = false, nullable = false, columnDefinition = "uuid")
    protected UUID id;

    @Column(nullable = false)
    private Integer cilindraje;

    @Column(unique = true, nullable = false)
    private String placa;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String anioFabricacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @Column(nullable = false)
    private Boolean activo;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate(){
        this.fechaCreacion = LocalDateTime.now();
        this.activo = true;
    }
}
