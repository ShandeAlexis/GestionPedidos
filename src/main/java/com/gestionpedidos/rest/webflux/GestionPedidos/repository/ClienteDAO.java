package com.gestionpedidos.rest.webflux.GestionPedidos.repository;

import com.gestionpedidos.rest.webflux.GestionPedidos.documentos.Cliente;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ClienteDAO extends ReactiveMongoRepository<Cliente,String> {
}
