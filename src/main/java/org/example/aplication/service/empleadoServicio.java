package org.example.aplication.service;

import org.example.domain.Tarea;

import java.util.List;

public interface empleadoServicio {

    List<Tarea> findAll();
    Tarea findbyNOMBRE(String nombre);
    void save (Tarea cita);
    void uptade(Tarea cita);
    void delete(String nombre);





}
