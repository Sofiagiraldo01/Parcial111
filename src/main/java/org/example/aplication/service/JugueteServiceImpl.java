package org.example.aplication.service;

import org.example.domain.juguete;
import org.example.interfaces.jugueteRepository;

import java.util.List;

public class JugueteServiceImpl implements JugueteService {
    private final jugueteRepository productoRepository;

    public JugueteServiceImpl(jugueteRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<juguete> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public juguete findById(int id) {
        return productoRepository.findById(id);
    }

    @Override
    public void save(juguete producto) {
        validarProducto(producto);
        productoRepository.save(producto);
    }

    @Override
    public void update(juguete producto) {
        validarProducto(producto);
        productoRepository.update(producto);
    }

    @Override
    public void delete(int id) {
        productoRepository.delete(id);
    }

    private void validarProducto(juguete producto) {
        if (producto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }
        if (producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser mayor a cero");
        }
    }
}
