package datos.repository;

import datos.model.Estudiante;
import java.util.List;
import java.util.Optional;

public interface IEstudianteRepository {
    boolean agregar(Estudiante e);
    boolean editar(Estudiante e);
    boolean eliminar(String id);
    List<Estudiante> listar();
    Optional<Estudiante> findById(String id);
}