package com.gestionpedidos.rest.webflux.GestionPedidos.repository;

import com.gestionpedidos.rest.webflux.GestionPedidos.documentos.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductoDAO extends ReactiveMongoRepository<Producto, String> {
}
