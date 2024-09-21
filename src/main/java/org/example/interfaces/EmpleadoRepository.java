package org.example.interfaces;

import org.example.domain.Empleado;

import java.util.List;

public interface EmpleadoRepository {
    List<Empleado> findAll();
    Empleado findById(int id);
    void save(Empleado paciente);
    void update(Empleado paciente);
    void delete(int id);
}
