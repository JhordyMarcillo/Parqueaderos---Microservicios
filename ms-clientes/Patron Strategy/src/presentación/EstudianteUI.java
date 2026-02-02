package presentación;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class EstudianteUI extends JFrame {

    private JTextField tfId;
    private JTextField tfNombres;
    private JTextField tfEdad;
    private JButton btnGuardar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JTable table;
    private DefaultTableModel tableModel;

    public EstudianteUI() {
        initComponents();
    }

    private void initComponents() {
        setTitle("CRUD Estudiantes - Strategy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 450);
        setLocationRelativeTo(null);

        // Componentes
        tfId = new JTextField(12);
        tfNombres = new JTextField(25);
        tfEdad = new JTextField(5);
        btnGuardar = new JButton("Guardar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        // Layout Formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; formPanel.add(tfId, gbc);

        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("Nombres:"), gbc);
        gbc.gridx = 1; formPanel.add(tfNombres, gbc);

        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1; formPanel.add(tfEdad, gbc);

        gbc.gridx = 0; gbc.gridy = 3; formPanel.add(btnGuardar, gbc);
        gbc.gridx = 1; formPanel.add(btnEditar, gbc);
        gbc.gridx = 0; gbc.gridy = 4; formPanel.add(btnEliminar, gbc);
        gbc.gridx = 1; formPanel.add(btnLimpiar, gbc);

        // Tabla
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombres", "Edad"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(tableModel);

        getContentPane().setLayout(new BorderLayout(8,8));
        getContentPane().add(formPanel, BorderLayout.WEST);
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
    }

    // --- Métodos para el Controlador ---

    public String getIdInput() { return tfId.getText().trim(); }
    public String getNombresInput() { return tfNombres.getText().trim(); }
    public String getEdadInput() { return tfEdad.getText().trim(); }

    public void setIdInput(String t) { tfId.setText(t); }
    public void setNombresInput(String t) { tfNombres.setText(t); }
    public void setEdadInput(String t) { tfEdad.setText(t); }

    public void setIdEditable(boolean b) { tfId.setEditable(b); }

    public void limpiarCampos() {
        tfId.setText("");
        tfNombres.setText("");
        tfEdad.setText("");
        tfId.setEditable(true);
        table.clearSelection();
    }

    public DefaultTableModel getTableModel() { return tableModel; }
    public JTable getTable() { return table; }

    public void mostrarMensaje(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public boolean confirmarEliminacion(String id) {
        return JOptionPane.showConfirmDialog(this, "¿Eliminar ID " + id + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    // Registro de Listeners
    public void addGuardarListener(ActionListener al) { btnGuardar.addActionListener(al); }
    public void addEditarListener(ActionListener al) { btnEditar.addActionListener(al); }
    public void addEliminarListener(ActionListener al) { btnEliminar.addActionListener(al); }
    public void addLimpiarListener(ActionListener al) { btnLimpiar.addActionListener(al); }
    public void addTablaMouseListener(MouseListener ml) { table.addMouseListener(ml); }
}