import datos.repository.EstudianteRepository;
import datos.repository.IEstudianteRepository;
import logica_negocio.EstudianteService;
import presentación.EstudianteController;
import presentación.EstudianteUI;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IEstudianteRepository repository = new EstudianteRepository() ;
            EstudianteService service = new EstudianteService(repository);
            EstudianteUI view = new EstudianteUI();
            new EstudianteController(view, service);
        });
    }
}