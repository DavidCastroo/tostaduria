package tostaduria.infrastructure.adapters.out.persistence;

import tostaduria.application.ports.out.OrderRepositoryPort;
import tostaduria.domain.entities.Order;

import java.util.ArrayList;
import java.util.List;

public class InMemoryOrderAdapter implements OrderRepositoryPort {

    // "Base de datos falsa": una lista simple donde se van agregando los pedidos.
    // No usamos Map aquí porque Order no tiene un identificador único (id) definido.
    private final List<Order> pedidos = new ArrayList<>();

    @Override
    public void guardarPedido(Order order) {
        pedidos.add(order);
    }

    // Método extra (no lo pide la interfaz, pero es útil para poder verificar
    // en tus pruebas que el pedido realmente se guardó).
    public List<Order> obtenerTodos() {
        return pedidos;
    }
}