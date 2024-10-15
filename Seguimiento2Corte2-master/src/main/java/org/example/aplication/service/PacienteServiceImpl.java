package org.example.aplication.service;

import org.example.domain.Paciente;
import org.example.interfaces.PacienteRepository;

import java.util.List;

public class PacienteServiceImpl implements PacienteService{
private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


    @Override
    public List<Paciente> findAll() {
        return PacienteRepository.findAll();
    }

    @Override
    public Paciente findById(int id) {
        return PacienteRepository.findById(id);
    }

    @Override
    public void save(Paciente paciente) {
        validarPaciente(paciente);
        PacienteRepository.save(paciente);
    }

    @Override
    public void update(Paciente paciente) {
        validarPaciente(paciente);
        PacienteRepository.update(paciente);
    }

    @Override
    public void delete(int id) {
        PacienteRepository.delete(id);
    }



    private void validarPaciente(Paciente paciente){

        if (paciente.getNombre().isEmpty()){
            throw new IllegalArgumentException("el nombre del paciente no puede estar vacio");
        }
        if (paciente.getId() <= 100){
            throw new IllegalArgumentException("el Id debe ser menor o igual a 100");
        }
    }


}
