package org.example.interfaces;

import jdk.dynalink.linker.LinkerServices;
import org.example.domain.Paciente;

import java.util.List;

public interface PacienteRepository {

    static List<Paciente> findAll();

    List<Paciente> findAll();

    static Paciente findById(int id);

    Paciente findById(int id);

    static void save(Paciente paciente);

    void save(Paciente paciente);

    static void update(Paciente paciente);

    void update(Paciente paciente);

    static void delete(int id);


}
