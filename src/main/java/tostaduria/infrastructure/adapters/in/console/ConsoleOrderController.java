package tostaduria.infrastructure.adapters.in.console;

import tostaduria.application.usecases.ProcessCoffeeOrderUseCase;
import tostaduria.domain.entities.Order;
import tostaduria.domain.enums.MetodoPreparacion;

public class ConsoleOrderController {

    private final ProcessCoffeeOrderUseCase processCoffeeOrderUseCase;

    public ConsoleOrderController(ProcessCoffeeOrderUseCase processCoffeeOrderUseCase) {
        this.processCoffeeOrderUseCase = processCoffeeOrderUseCase;
    }

    public void procesarPedido(String tipoGrano, int cantidadGramos, MetodoPreparacion metodoPreparacion) {
        try {
            Order order = processCoffeeOrderUseCase.ejecutar(tipoGrano, cantidadGramos, metodoPreparacion);
            System.out.println("\n✅ Pedido confirmado");
            System.out.println("-----------------------------");
            System.out.println("Grano:      " + order.getTipoGrano());
            System.out.println("Cantidad:   " + order.getCantidadGramos() + "g");
            System.out.println("Preparación: " + order.getMetodoPreparacion());
            System.out.println("-----------------------------");
        } catch (Exception e) {
            System.out.println("\n❌ Pedido rechazado: " + e.getMessage());
        }
    }
}