package org.example.aplication.service;

import org.example.domain.Tarea;

import java.util.List;

public class EmpleadoServicioIMPL implements empleadoServicio {


    public EmpleadoServicioIMPL(EmpleadoServicioIMPL empleadopService) {
    }



    @Override
    public List<Tarea> findAll() {
        return List.of();
    }

    @Override
    public Tarea findbyNOMBRE(String nombre) {
        return null;
    }

    @Override
    public void save(Tarea cita) {

    }

    @Override
    public void uptade(Tarea cita) {

    }

    @Override
    public void delete(String nombre) {

    }
}
