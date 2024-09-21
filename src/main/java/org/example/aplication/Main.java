package org.example.aplication;


import org.example.aplication.service.EmpleadoServicioIMPL;

import org.example.domain.Tarea;
import org.example.domain.Empleado;
import org.example.interfaces.TareaRepository;
import org.example.interfaces.EmpleadoRepository;

import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    private static EmpleadoServicioIMPL empleadopService = null;
    private static TareaService citaService;
    private static Object id;


    static {
        EmpleadoRepository pacienteRepository = new org.example.infraestructure.repositoryImpl.EmpleadoRepositoryImpl();
        empleadopService= new EmpleadoServicioIMPL(empleadopService);
        TareaRepository citaRepository = new org.example.infraestructure.repositoryImpl.TareaRepositoryImpl();
        citaService = new CitaServiceImpl(citaRepository);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            String opcion = JOptionPane.showInputDialog(
                    "1. lista de empleados\n" +
                            "2. Crear un empleado nuevo \n" +
                            "3. Actualizar un empleado que ya esta en la lista\n" +
                            "4. Eliminar empleado de la lista \n" +
                            "5. Lista de empleados\n" +
                            "6. Crear nueva empleado\n" +
                            "7. Actualizar un empleado\n" +
                            "8. Eliminar una empleado\n" +
                            "9. Salir\n"

            );

            if (opcion == null) {
                salir = true;
                continue;
            }

            switch (opcion) {
                case "1":
                    listarPacientes();
                    break;
                case "2":
                    crearPaciente();
                    break;
                case "3":
                    actualizarPaciente();
                    break;
                case "4":
                    eliminarPaciente();
                    break;
                case "5":
                    listasCitas();
                    break;
                case "6":

                    crearCita();
                    break;
                case "7":
                    actualizarCita(id);
                    break;
                case "8":
                    eliminarCita();
                    break;
                case "9":
                    salir = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }

    private static void actualizarCita(Object id) {
    }

    private static void listasCitas() {
        List<Tarea> citas = citaService.findAllTarea();
        if (citas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay citas registradas");
        } else {
            StringBuilder mensaje = new StringBuilder("Listado de citas:\n");
            for (Tarea cita : citas) {
                mensaje.append(cita).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensaje.toString());
        }
    }

    private static void crearCita() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        Date fecha = null;
        Date hora = null;

        while (fecha == null) {
            String fechaInput = JOptionPane.showInputDialog("Ingrese la fecha de la cita (formato YYYY-MM-DD):");
            try {
                fecha = dateFormat.parse(fechaInput);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Error: Formato de fecha inválido.");
            }
        }

        while (hora == null) {
            String horaInput = JOptionPane.showInputDialog("Ingrese la hora de la cita (formato HH:MM):");
            try {
                hora = timeFormat.parse(horaInput);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Error: Formato de hora inválido.");
            }
        }

        String idPacienteInput = JOptionPane.showInputDialog("Ingrese el ID del paciente para la cita:");
        int idPaciente = Integer.parseInt(idPacienteInput);



        Empleado paciente = citaService.finalize(idPaciente);
        if (paciente == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el paciente con ID " + idPaciente);
            return;
        }

        String motivoInput = JOptionPane.showInputDialog("Ingrese el motivo de la cita:");

        List<Tarea> citas = citaService.findAllCita(idPacienteInput);
        int idCita = citas.stream().mapToInt(Tarea::getIdCita).max().orElse(0) + 1;

        Tarea nuevaCita = new Tarea();
        nuevaCita.setIdCita(idCita);
        nuevaCita.setFechafin(fecha);
        nuevaCita.setFechafin(fecha);
        nuevaCita.setEmpleado(new Empleado());
        nuevaCita.setEstado(nuevaCita.getEstado());
        try {
            try {
                citaService.wait(idCita);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            JOptionPane.showMessageDialog(null, "Cita creada exitosamente con ID: " + idCita);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private static <Tareas> void listarPacientes() {
        List<Tarea> pacientes = empleadopService.findAll();
        if (pacientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pacientes registrados.");
        } else {
            StringBuilder mensaje = new StringBuilder("Listado de pacientes:\n");
            for (Tarea paciente : pacientes) {
                mensaje.append(paciente).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensaje.toString());
        }
    }

    private static void crearPaciente() {
        String codInput = JOptionPane.showInputDialog("Ingrese el código del paciente:");
        int cod = Integer.parseInt(codInput);

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del paciente:");
        String edadInput = JOptionPane.showInputDialog("Ingrese la edad del paciente:");
        int edad = Integer.parseInt(edadInput);

        String genero = JOptionPane.showInputDialog("Ingrese el género del paciente:");
        String direccion = JOptionPane.showInputDialog("Ingrese la dirección del paciente:");
        String telefonoInput = JOptionPane.showInputDialog("Ingrese el número de teléfono del paciente:");
        long telefono = Long.parseLong(telefonoInput);

        Empleado paciente = new Empleado();
        paciente.setId(cod);
        paciente.setNombre(nombre);
        paciente.setEdad(edad);
        paciente.setGenero(genero);
        paciente.setDireccion(direccion);
        paciente.setTelefono(telefono);

        try {
            citaService.wait(paciente);
            JOptionPane.showMessageDialog(null, "Paciente creado exitosamente.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private static void actualizarCita(int id) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        String idCitaInput = JOptionPane.showInputDialog("Ingrese el ID de la cita:");
        int idCita = Integer.parseInt(idCitaInput);

        List<Tarea> cita = citaService.findAllCita(idCitaInput);
        if (cita == null) {
            JOptionPane.showMessageDialog(null, "No se encontró la cita con ID " + idCita);
            return;
        }

        String newFecha = JOptionPane.showInputDialog("Ingrese la nueva fecha de la cita (dejar en blanco para no cambiar):");
        if (!newFecha.isEmpty()) {
            try {
                Date fecha = dateFormat.parse(newFecha);

            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Error en el formato de la fecha.");
                return;
            }
        }

        String newHora = JOptionPane.showInputDialog("Ingrese la nueva hora de la cita (dejar en blanco para no cambiar):");
        if (!newHora.isEmpty()) {
            try {
                Date hora = timeFormat.parse(newHora);
                Date date = null;

            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Error en el formato de la hora.");
                return;
            }
        }

        String motivo = JOptionPane.showInputDialog("Ingrese el nuevo motivo de la cita (dejar en blanco para no cambiar):");
        if (!motivo.isEmpty()) {
            cita.remove(motivo);
        }

        try {

            JOptionPane.showMessageDialog(null, "Cita actualizada exitosamente.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private static void actualizarPaciente() {
        String idInput = JOptionPane.showInputDialog("Ingrese el ID del paciente a actualizar:");
        int id = Integer.parseInt(idInput);

        List<Tarea> paciente = citaService.findAllCita(idInput);
        if (paciente == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el paciente con ID " + id);
            return;
        }

        String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del paciente (dejar en blanco para no cambiar):");
        if (!nombre.isEmpty()) {


        }

        String edadInput = JOptionPane.showInputDialog("Ingrese la nueva edad del paciente (dejar en blanco para no cambiar):");
        if (!edadInput.isEmpty()) {
            int edad = Integer.parseInt(edadInput);


        }

        String genero = JOptionPane.showInputDialog("Ingrese el nuevo género del paciente (dejar en blanco para no cambiar):");
        if (!genero.isEmpty()) {
            int Stringnombre = 0;
            return;
        }

        String direccion = JOptionPane.showInputDialog("Ingrese la nueva dirección del paciente (dejar en blanco para no cambiar):");
        if (!direccion.isEmpty()) {
            paciente.stream();
        }

        String telefonoInput = JOptionPane.showInputDialog("Ingrese el nuevo número de teléfono del paciente (dejar en blanco para no cambiar):");
        if (!telefonoInput.isEmpty()) {
            long telefono = Long.parseLong(telefonoInput);


        }

        try {
            try {
                citaService.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            JOptionPane.showMessageDialog(null, "Paciente actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private static void eliminarCita() {
        String idInput = JOptionPane.showInputDialog("Ingrese el ID de la cita a eliminar:");
        int id = Integer.parseInt(idInput);


        Tarea cita = (Tarea) citaService.findAllCita(String.valueOf(id));
        if (cita == null) {
            JOptionPane.showMessageDialog(null, "No se encontró ninguna cita con el ID " + id);
            return;
        }

        citaService.findAllCita(idInput);
        JOptionPane.showMessageDialog(null, "Cita eliminada exitosamente.");
    }

    private static <id, idm> void eliminarPaciente() {
        String idInput = JOptionPane.showInputDialog("Ingrese el ID del paciente a eliminar:");
        int id = Integer.parseInt(idInput);

        List<Tarea> paciente = citaService.findAllCita(idInput);
        if (paciente == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el paciente con ID " + id);
            return;
        }

        int id1 = id;
        JOptionPane JOptonPane;
        JOptionPane.showMessageDialog(null, "Paciente eliminado exitosamente.");
    }
}

