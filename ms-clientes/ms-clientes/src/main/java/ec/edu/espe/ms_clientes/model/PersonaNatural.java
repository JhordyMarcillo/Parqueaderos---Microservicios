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
@Table(name = "persona_natural")
@PrimaryKeyJoinColumn(name = "persona_id")
@DiscriminatorValue("NATURAL")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PersonaNatural extends Persona {
    @Column(unique = true, nullable = false)
    private String apellido;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GeneroType genero;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Override
    public boolean validarIdentificacion(){
        String cedula = this.getIdentificacion();

        if (cedula == null || cedula.length() != 10 || !cedula.matches("\\d+")) {
            return false;
        }

        try {
            int provincia = Integer.parseInt(cedula.substring(0, 2));
            if (provincia < 0 || provincia > 24) return false;

            int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
            if (tercerDigito >= 6) return false;

            int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};
            int total = 0;

            for (int i = 0; i < 9; i++) {
                int valor = Integer.parseInt(String.valueOf(cedula.charAt(i))) * coeficientes[i];
                total += (valor > 9) ? valor - 9 : valor;
            }

            int digitoVerificador = Integer.parseInt(String.valueOf(cedula.charAt(9)));
            int decenaSuperior = (total % 10 == 0) ? total : (total / 10 + 1) * 10;
            int resultado = decenaSuperior - total;

            return resultado == digitoVerificador;

        } catch (NumberFormatException e) {
            return false;
        }
    }
}
