package com.gestionpedidos.rest.webflux.GestionPedidos.controller;

import com.gestionpedidos.rest.webflux.GestionPedidos.documentos.Producto;
import com.gestionpedidos.rest.webflux.GestionPedidos.services.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public Mono<ResponseEntity<Flux<Producto>>> listarPorductos(){
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(productoService.findAll())
        );
    }
    @GetMapping("{id}")
    public Mono<ResponseEntity<Producto>> verProducto(@PathVariable String id){
        return productoService.findById(id).map(p-> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(p)
        ).defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> guardarProducto(@Valid @RequestBody Mono<Producto> monoProducto){
        Map<String, Object> respuesta = new HashMap<>();
        return monoProducto.flatMap(producto -> {
            return productoService.save(producto).map(p->{
                respuesta.put("producto", p);
                respuesta.put("mensaje", "Producto guardado con éxito");
                respuesta.put("timestamp", new Date());
                return ResponseEntity
                        .created(URI.create("/api/productos/".concat(p.getId())))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(respuesta);
            });
        }).onErrorResume(t->{
            return Mono.just(t).cast(WebExchangeBindException.class)
                    .flatMap(e-> Mono.just(e.getFieldErrors()))
                    .flatMapMany(Flux::fromIterable)
                    .map(fieldError -> "El campo :"+fieldError.getField()+" "+fieldError.getDefaultMessage())
                    .collectList()
                    .flatMap(list->{
                        respuesta.put("errors", list);
                        respuesta.put("timestamp", new Date());
                        respuesta.put("status", HttpStatus.BAD_REQUEST.value());
                        return Mono.just(ResponseEntity.badRequest().body(respuesta));
                    });
        });
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Producto>> editarProducto(@PathVariable String id, @RequestBody Producto producto){
        return productoService.findById(id).flatMap(p-> {
            p.setNombre(producto.getNombre());
            p.setStock(producto.getStock());
            p.setPrecio(producto.getPrecio());
            return productoService.save(p);
        }).map(p->ResponseEntity.created(URI.create("/api/productos/".concat(p.getId())))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(p)
        ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminarProducto(@PathVariable String id){
        return productoService.findById(id).flatMap(p->{
            return productoService.delete(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

}
