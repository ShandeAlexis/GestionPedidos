package com.gestionpedidos.rest.webflux.GestionPedidos.services;

import com.gestionpedidos.rest.webflux.GestionPedidos.documentos.Pedido;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PedidoService {
    Flux<Pedido> findAll();

    Mono<Pedido> findById(String id);

    Mono<Pedido> save(Pedido pedido);

    Mono<Void> delete(Pedido pedido);
}
