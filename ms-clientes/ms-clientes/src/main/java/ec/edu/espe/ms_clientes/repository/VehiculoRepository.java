package ec.edu.espe.ms_clientes.repository;

import ec.edu.espe.ms_clientes.model.Persona;
import ec.edu.espe.ms_clientes.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, UUID> {

    @Query(value="SELECT * FROM p_buscar_vehiculo_dado_p()", nativeQuery=true)
    List<Vehiculo> findByPropietario(String identificacion);

    Optional<Vehiculo> findByPlaca(String placa);

    List<Vehiculo> getVehiculosByPersona(String identificacion);
    List<Vehiculo> findByPersona(Persona persona);

}
