package tostaduria.application.ports.out;

import tostaduria.domain.entities.Order;

public interface OrderRepositoryPort {
    void guardarPedido(Order order);
}