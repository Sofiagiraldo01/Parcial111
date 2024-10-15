package org.example.infraestructure.repository;

import org.example.domain.juguete;
import org.example.interfaces.jugueteRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class jugueteRepositoryImpl implements jugueteRepository {
    private static final String FILE_NAME = "productos.dat";

    @Override
    public List<juguete> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<juguete>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public juguete findById(int id) {
        return findAll().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(juguete producto) {
        List<juguete> productos = findAll();
        productos.add(producto);
        saveAll(productos);
    }

    @Override
    public void update(juguete producto) {
        List<juguete> productos = findAll();
        productos = productos.stream()
                .map(p -> p.getId() == producto.getId() ? producto : p)
                .collect(Collectors.toList());
        saveAll(productos);
    }

    @Override
    public void delete(int id) {
        List<juguete> productos = findAll();
        productos = productos.stream()
                .filter(p -> p.getId() != id)
                .collect(Collectors.toList());
        saveAll(productos);
    }

    private void saveAll(List<juguete> productos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(productos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
