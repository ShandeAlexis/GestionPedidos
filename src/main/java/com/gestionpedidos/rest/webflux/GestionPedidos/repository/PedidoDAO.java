package com.gestionpedidos.rest.webflux.GestionPedidos.repository;

import com.gestionpedidos.rest.webflux.GestionPedidos.documentos.Pedido;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PedidoDAO extends ReactiveMongoRepository<Pedido, String> {
}
