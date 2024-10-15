package org.example.aplication.service;

import org.example.domain.Paciente;

import java.util.List;

public interface PacienteService {

    static List<Paciente> findAll();

    List<Paciente> findAll();

    static Paciente findById(int id);

    static void save(Paciente paciente);

    static void update(Paciente paciente);

}
