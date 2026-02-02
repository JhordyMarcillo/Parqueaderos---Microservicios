package ec.edu.espe.ms_clientes.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@Table(name = "auto")
@EqualsAndHashCode(callSuper = false)
@DiscriminatorValue("AUTO")
@PrimaryKeyJoinColumn(name = "vehiculo_id")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class AutoFamiliar extends Vehiculo{
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AutoType tipoAuto;

    @Column(nullable = false)
    private String tipoCombustible;

    @Column(nullable = false)
    private Integer numeroPuertas;

    @Column(nullable = false)
    private Double capacidadMaleteroLitros;

    @Column(nullable = false)
    private Integer capacidadOcupantes;

    @Column(nullable = false)
    private String transmision;
}
