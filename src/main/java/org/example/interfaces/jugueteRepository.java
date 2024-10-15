package org.example.interfaces;

import org.example.domain.juguete;

import java.util.List;

public interface jugueteRepository {
    List<juguete> findAll();
    juguete findById(int id);
    void save(juguete producto);
    void update(juguete producto);
    void delete(int id);
}