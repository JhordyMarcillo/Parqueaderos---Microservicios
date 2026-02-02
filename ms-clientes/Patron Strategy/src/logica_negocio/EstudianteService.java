package logica_negocio;

import datos.model.Estudiante;
import datos.repository.IEstudianteRepository;

import java.util.List;

public class EstudianteService {

    // Referencia Strategy
    private final IEstudianteRepository repo;

    public EstudianteService(IEstudianteRepository repo) {
        this.repo = repo;
    }

    public ResultadoOperacion agregar(Estudiante e) {
        if (validarDatosBasicos(e)) return ResultadoOperacion.error("Datos inválidos.");
        if (repo.findById(e.getId()).isPresent()) return ResultadoOperacion.error("ID ya registrado.");

        boolean ok = repo.agregar(e);
        return ok ? ResultadoOperacion.ok("Estudiante agregado.") : ResultadoOperacion.error("No se pudo agregar.");
    }

    public ResultadoOperacion editar(Estudiante e) {
        if (validarDatosBasicos(e)) return ResultadoOperacion.error("Datos inválidos.");
        if (!repo.findById(e.getId()).isPresent()) return ResultadoOperacion.error("ID no existe.");

        boolean ok = repo.editar(e);
        return ok ? ResultadoOperacion.ok("Estudiante editado.") : ResultadoOperacion.error("No se pudo editar.");
    }

    public ResultadoOperacion eliminar(String id) {
        if (id == null || id.trim().isEmpty()) return ResultadoOperacion.error("ID vacío.");
        if (!repo.findById(id).isPresent()) return ResultadoOperacion.error("ID no existe.");

        boolean ok = repo.eliminar(id);
        return ok ? ResultadoOperacion.ok("Estudiante eliminado.") : ResultadoOperacion.error("No se pudo eliminar.");
    }

    public List<Estudiante> listar() {
        return repo.listar();
    }

    private boolean validarDatosBasicos(Estudiante e) {
        return e == null ||
                e.getId() == null || e.getId().trim().isEmpty() ||
                e.getNombres() == null || e.getNombres().trim().isEmpty() ||
                e.getEdad() <= 0;
    }

    // Clase interna estática para respuestas
    public static class ResultadoOperacion {
        private final boolean success;
        private final String mensaje;

        private ResultadoOperacion(boolean success, String mensaje) {
            this.success = success;
            this.mensaje = mensaje;
        }

        public static ResultadoOperacion ok(String mensaje) {
            return new ResultadoOperacion(true, mensaje);
        }

        public static ResultadoOperacion error(String mensaje) {
            return new ResultadoOperacion(false, mensaje);
        }

        public boolean isSuccess() { return success; }
        public String getMensaje() { return mensaje; }
    }
}