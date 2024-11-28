package com.gestionpedidos.rest.webflux.GestionPedidos.services;

import com.gestionpedidos.rest.webflux.GestionPedidos.documentos.Producto;
import com.gestionpedidos.rest.webflux.GestionPedidos.repository.ProductoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class ProductoServiceImpl implements ProductoService{
    @Autowired
    private ProductoDAO productoDAO;
    @Override
    public Flux<Producto> findAll() {
        return productoDAO.findAll();
    }

    @Override
    public Mono<Producto> findById(String id) {
        return productoDAO.findById(id);
    }

    @Override
    public Mono<Producto> save(Producto producto) {
        return productoDAO.save(producto);
    }

    @Override
    public Mono<Void> delete(Producto producto) {
        return productoDAO.delete(producto);
    }
}
