package org.example.aplication.service;

import org.example.domain.juguete;

import java.util.List;

public interface JugueteService {
    List<juguete> findAll();
    juguete findById(int id);
    void save(juguete producto);
    void update(juguete producto);
    void delete(int id);
}