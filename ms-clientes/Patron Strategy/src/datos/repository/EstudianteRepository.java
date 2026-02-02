package datos.repository;

import datos.model.Estudiante;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EstudianteRepository implements IEstudianteRepository {
    private static final String STORAGE_FILE = "students.ser";
    private final List<Estudiante> estudiantes;

    // Constructor público normal
    public EstudianteRepository() {
        this.estudiantes = loadFromFile();
    }

    @Override
    public synchronized boolean agregar(Estudiante e) {
        if (e == null) return false;
        boolean added = estudiantes.add(e);
        if (added) saveToFile();
        return added;
    }

    @Override
    public synchronized boolean editar(Estudiante e) {
        if (e == null) return false;
        Optional<Estudiante> found = findById(e.getId());
        if (found.isPresent()) {
            Estudiante orig = found.get();
            orig.setNombres(e.getNombres());
            orig.setEdad(e.getEdad());
            saveToFile();
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean eliminar(String id) {
        boolean removed = estudiantes.removeIf(st -> st.getId().equals(id));
        if (removed) saveToFile();
        return removed;
    }

    @Override
    public synchronized List<Estudiante> listar() {
        return Collections.unmodifiableList(new ArrayList<>(estudiantes));
    }

    @Override
    public synchronized Optional<Estudiante> findById(String id) {
        return estudiantes.stream().filter(st -> st.getId().equals(id)).findFirst();
    }

    @SuppressWarnings("unchecked")
    private List<Estudiante> loadFromFile() {
        File f = new File(STORAGE_FILE);
        if (!f.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                return (List<Estudiante>) obj;
            }
        } catch (Exception ex) {
            System.err.println("Info: Creando nuevo almacenamiento.");
        }
        return new ArrayList<>();
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STORAGE_FILE))) {
            oos.writeObject(new ArrayList<>(estudiantes));
        } catch (IOException ex) {
            System.err.println("Error guardando estudiantes: " + ex.getMessage());
        }
    }
}