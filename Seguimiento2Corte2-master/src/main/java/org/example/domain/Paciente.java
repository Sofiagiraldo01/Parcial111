package org.example.domain;

import java.io.Serializable;

public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private int id;

    public Paciente(){

    }

    public Paciente(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }
}
