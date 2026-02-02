package presentación;

import datos.model.Estudiante;
import logica_negocio.EstudianteService;
import logica_negocio.EstudianteService.ResultadoOperacion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class EstudianteController {

    private final EstudianteUI view;
    private final EstudianteService service;

    public EstudianteController(EstudianteUI view, EstudianteService service) {
        this.view = view;
        this.service = service;

        initController();
    }

    private void initController() {
        // Asignar listeners a la vista
        view.addGuardarListener(e -> guardar());
        view.addEditarListener(e -> editar());
        view.addEliminarListener(e -> eliminar());
        view.addLimpiarListener(e -> view.limpiarCampos());

        view.addTablaMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) seleccionarEstudiante();
            }
        });

        cargarTabla();
        view.setVisible(true);
    }

    private void cargarTabla() {
        view.getTableModel().setRowCount(0);
        List<Estudiante> lista = service.listar();
        for (Estudiante s : lista) {
            view.getTableModel().addRow(new Object[]{s.getId(), s.getNombres(), s.getEdad()});
        }
    }

    private void guardar() {
        try {
            String id = view.getIdInput();
            String nombres = view.getNombresInput();
            int edad = Integer.parseInt(view.getEdadInput());

            Estudiante est = new Estudiante(id, nombres, edad);
            ResultadoOperacion res = service.agregar(est);

            procesarResultado(res);
        } catch (NumberFormatException ex) {
            view.mostrarError("Edad debe ser numérica.");
        }
    }

    private void editar() {
        try {
            String id = view.getIdInput();
            String nombres = view.getNombresInput();
            int edad = Integer.parseInt(view.getEdadInput());

            Estudiante est = new Estudiante(id, nombres, edad);
            ResultadoOperacion res = service.editar(est);

            procesarResultado(res);
        } catch (NumberFormatException ex) {
            view.mostrarError("Edad debe ser numérica.");
        }
    }

    private void eliminar() {
        String id = view.getIdInput();
        if (id.isEmpty()) {
            view.mostrarError("Seleccione o escriba un ID.");
            return;
        }

        if (view.confirmarEliminacion(id)) {
            ResultadoOperacion res = service.eliminar(id);
            procesarResultado(res);
        }
    }

    private void seleccionarEstudiante() {
        int row = view.getTable().getSelectedRow();
        if (row >= 0) {
            view.setIdInput((String) view.getTableModel().getValueAt(row, 0));
            view.setNombresInput((String) view.getTableModel().getValueAt(row, 1));
            view.setEdadInput(String.valueOf(view.getTableModel().getValueAt(row, 2)));
            view.setIdEditable(false);
        }
    }

    private void procesarResultado(ResultadoOperacion res) {
        if (res.isSuccess()) {
            view.mostrarMensaje(res.getMensaje());
            view.limpiarCampos();
            cargarTabla();
        } else {
            view.mostrarError(res.getMensaje());
        }
    }
}