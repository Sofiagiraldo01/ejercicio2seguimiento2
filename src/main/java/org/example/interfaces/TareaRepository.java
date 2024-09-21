package org.example.interfaces;

import org.example.domain.Tarea;

import java.util.List;

public interface TareaRepository {
    List<Tarea> findAllCita();
    Tarea findByIdCita(int idCita);
    void saveCita(Tarea cita);
    void updateCita(Tarea cita);
    void deleteCita(int idCita);
}
