package com.gestionpedidos.rest.webflux.GestionPedidos.controller;

import com.gestionpedidos.rest.webflux.GestionPedidos.documentos.Pedido;
import com.gestionpedidos.rest.webflux.GestionPedidos.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    @GetMapping
    public Mono<ResponseEntity<Flux<Pedido>>> listarPedidos(){
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(pedidoService.findAll())
        );
    }
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Pedido>> verPedido(@PathVariable String id){
        return pedidoService.findById(id).map(p-> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(p)
        ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> guardarPedido(@Valid @RequestBody Mono<Pedido> pedidoMono) {
        Map<String, Object> respuesta = new HashMap<>();
        return pedidoMono.flatMap(pedido -> {
            // Calcula el total del pedido utilizando los detalles
            pedido.calcularTotal();

            // Si no hay fecha, asigna la fecha actual
            if (pedido.getFecha() == null) {
                pedido.setFecha(new Date());
            }

            return pedidoService.save(pedido).map(p -> {
                respuesta.put("pedido", p);
                respuesta.put("mensaje", "Pedido guardado con éxito");
                respuesta.put("timestamp", new Date());
                return ResponseEntity.created(URI.create("/api/pedidos/".concat(p.getId())))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(respuesta);
            });
        });
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminarPedido(@PathVariable String id){
        return pedidoService.findById(id).flatMap(p->pedidoService.delete(p)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
        ).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

}
