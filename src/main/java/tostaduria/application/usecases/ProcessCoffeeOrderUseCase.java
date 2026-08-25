package tostaduria.application.usecases;

import tostaduria.application.ports.out.InventoryPort;
import tostaduria.application.ports.out.OrderRepositoryPort;
import tostaduria.domain.entities.CoffeeBean;
import tostaduria.domain.entities.Order;
import tostaduria.domain.enums.MetodoPreparacion;

public class ProcessCoffeeOrderUseCase {

    private final InventoryPort inventoryPort;
    private final OrderRepositoryPort orderRepositoryPort;

    public ProcessCoffeeOrderUseCase(InventoryPort inventoryPort, OrderRepositoryPort orderRepositoryPort) {
        this.inventoryPort = inventoryPort;
        this.orderRepositoryPort = orderRepositoryPort;
    }

    public Order ejecutar(String tipoGrano, int cantidadGramos, MetodoPreparacion metodoPreparacion) {
        CoffeeBean coffeeBean = inventoryPort.buscarPorTipo(tipoGrano);   // Paso 1
        coffeeBean.descontarStock(cantidadGramos);                       // Paso 2 (valida y descuenta)
        inventoryPort.actualizarInventario(coffeeBean);                  // Paso 3 (persiste el cambio)
        Order order = new Order(tipoGrano, cantidadGramos, metodoPreparacion); // Paso 4 (crea el pedido)
        orderRepositoryPort.guardarPedido(order);                        // Paso 5 (persiste el pedido)
        return order;                                                    // Paso 6 (lo devuelve)
    }
}