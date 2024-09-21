package org.example.domain;
import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Tarea implements Serializable {
    private static final long serialVersionaUID = 1L;
    private int idCita;
    private Date fechainicio;
    private Date fechafin;
    private String titulo;
    private String descripcion;
    private String estado;
    private Empleado empleado ;

    public Tarea(){
    }

    public Tarea(int idCita, Date fechainicio, Date fechafin, String titulo, String descripcion,String estado, Empleado empleado) {
        this.idCita = idCita;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.empleado = empleado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "idCita=" + idCita +
                ", fechainicio=" + fechainicio +
                ", fechafin=" + fechafin +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", empleado=" + empleado +
                '}';
    }
}
