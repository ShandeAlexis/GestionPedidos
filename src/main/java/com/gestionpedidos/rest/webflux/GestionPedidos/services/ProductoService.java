package com.gestionpedidos.rest.webflux.GestionPedidos.services;

import com.gestionpedidos.rest.webflux.GestionPedidos.documentos.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoService {
    public Flux<Producto> findAll();
    public Mono<Producto> findById(String id);
    public Mono<Producto> save(Producto producto);
    public Mono<Void> delete(Producto producto);

}
