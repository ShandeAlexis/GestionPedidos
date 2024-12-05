package com.gestionpedidos.rest.webflux.GestionPedidos.documentos;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "pedidos")
public class Pedido {
    @Id
    private String id;
    @NotNull
    private Cliente cliente;
    @NotNull
    private List<DetallePedido> detalles;
    private Double total;
    private Date fecha;

    public Pedido() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    // Calcular el total del pedido
    public void calcularTotal() {
        this.total = detalles.stream()
                .mapToDouble(DetallePedido::calcularSubtotal)
                .sum();
    }
}
