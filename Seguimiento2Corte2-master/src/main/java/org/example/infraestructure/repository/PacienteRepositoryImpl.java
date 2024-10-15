package org.example.infraestructure.repository;

import org.example.domain.Paciente;
import org.example.interfaces.PacienteRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PacienteRepositoryImpl implements PacienteRepository {
private static final String FILE_NAME = "paciente.dat";






    @Override
    public List<Paciente> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))){
            return (ArrayList<Paciente>) ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            return new ArrayList<>();
        }
    }

    @Override
    public Paciente findById(int id) {
        return findAll().stream()
                .filter(paciente -> paciente.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Paciente paciente) {
List<Paciente> pacientes = findAll();
pacientes.add(paciente);
saveAll(pacientes);
    }


    private void saveAll(List<Paciente> pacientes) {
    }


    @Override
    public void update(Paciente paciente) {

        List<Paciente> pacientes = findAll();
        pacientes = pacientes.stream()
                .map(paciente1 -> paciente1.getId() == paciente1.getId() ? paciente1 : paciente1)
                .collect(Collectors.toList());
        saveAll(pacientes);

    }

    @Override
    public void delete(int id) {


        List<Paciente> pacientes = findAll();


        List<Paciente> pacientes1 = findAll();
        pacientes1 = pacientes1.stream()
                .filter(p -> p.getId() != id)
                .collect(Collectors.toList());
        saveAll(pacientes1);




    }

    private void collect(Collector<Object,?, List<Object>> list) {
    }
}
