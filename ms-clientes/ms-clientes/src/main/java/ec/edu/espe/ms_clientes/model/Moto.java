package ec.edu.espe.ms_clientes.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@Table(name = "moto")
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("MOTO")
@PrimaryKeyJoinColumn(name = "vehiculo_id")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 //actua solo sobre la entidad
public class Moto extends Vehiculo {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MotoType tipo;

    @Column(nullable = false)
    private Boolean tieneCasco;

}
