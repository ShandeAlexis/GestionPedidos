package com.gestionpedidos.rest.webflux.GestionPedidos.documentos;

import jakarta.validation.constraints.NotNull;

public class DetallePedido {
    @NotNull
    private Producto producto;
    @NotNull
    private Integer cantidad;

    public DetallePedido() {
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    // calcular subTotal del producto
    public Double calcularSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}
