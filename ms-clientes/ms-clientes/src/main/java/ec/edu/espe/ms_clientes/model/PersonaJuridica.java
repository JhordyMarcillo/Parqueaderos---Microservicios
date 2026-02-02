package ec.edu.espe.ms_clientes.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "persona_juridica")
@PrimaryKeyJoinColumn(name = "persona_id")
@DiscriminatorValue("JURIDICA")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PersonaJuridica extends Persona{

    @Column(unique = true, nullable = false)
    private String razonSocial;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActividadType actividadEconomica;

    @Column(nullable = false)
    private String representanteLegal;

    @Column(nullable = false)
    private LocalDate fechaConstitucion;

    @Override
    public boolean validarIdentificacion(){
        String ruc = this.getIdentificacion();

        if (ruc == null || ruc.length() != 13 || !ruc.matches("\\d+")) {
            return false;
        }

        if (!ruc.endsWith("001")) {
            return false;
        }

        int provincia = Integer.parseInt(ruc.substring(0, 2));
        if (provincia < 1 || provincia > 24) return false;

        return true;
    }
}
