package com.gestionpedidos.rest.webflux.GestionPedidos.services;

import com.gestionpedidos.rest.webflux.GestionPedidos.documentos.Pedido;
import com.gestionpedidos.rest.webflux.GestionPedidos.repository.PedidoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class PedidoServiceImpl implements PedidoService{
    @Autowired
    private PedidoDAO pedidoDAO;
    @Override
    public Flux<Pedido> findAll() {
        return pedidoDAO.findAll();
    }

    @Override
    public Mono<Pedido> findById(String id) {
        return pedidoDAO.findById(id);
    }

    @Override
    public Mono<Pedido> save(Pedido pedido) {
        return pedidoDAO.save(pedido);
    }

    @Override
    public Mono<Void> delete(Pedido pedido) {
        return pedidoDAO.delete(pedido);
    }
}
