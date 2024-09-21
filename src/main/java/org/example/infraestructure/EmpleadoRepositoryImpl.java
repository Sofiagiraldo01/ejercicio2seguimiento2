package org.example.infraestructure.repositoryImpl;

import org.example.domain.Empleado;
import org.example.interfaces.EmpleadoRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmpleadoRepositoryImpl implements EmpleadoRepository {
    private static final String FILE_NAME = "productos.dat";

    @Override
    public List<Empleado> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Empleado>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Empleado findById(int id) {
        return findAll().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Empleado paciente) {
        List<Empleado> pacientes = findAll();
        pacientes.add(paciente);
        saveAll(pacientes);
    }

    @Override
    public void update(Empleado paciente) {
        List<Empleado> pacientes = findAll();
        pacientes = pacientes.stream()
                .map(p -> p.getId() == paciente.getId() ? paciente : p)
                .collect(Collectors.toList());
        saveAll(pacientes);
    }

    @Override
    public void delete(int id) {
        List<Empleado> pacientes = findAll();
        pacientes = pacientes.stream()
                .filter(p -> p.getId() != id)
                .collect(Collectors.toList());
        saveAll(pacientes);
    }

    private void saveAll(List<Empleado> pacientes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(pacientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
