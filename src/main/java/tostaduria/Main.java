package tostaduria;

import tostaduria.application.ports.out.InventoryPort;
import tostaduria.application.ports.out.OrderRepositoryPort;
import tostaduria.application.usecases.ProcessCoffeeOrderUseCase;
import tostaduria.domain.enums.MetodoPreparacion;
import tostaduria.infrastructure.adapters.in.console.ConsoleOrderController;
import tostaduria.infrastructure.adapters.out.persistence.InMemoryInventoryAdapter;
import tostaduria.infrastructure.adapters.out.persistence.InMemoryOrderAdapter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryPort inventoryPort = new InMemoryInventoryAdapter();
        OrderRepositoryPort orderRepositoryPort = new InMemoryOrderAdapter();
        ProcessCoffeeOrderUseCase useCase = new ProcessCoffeeOrderUseCase(inventoryPort, orderRepositoryPort);
        ConsoleOrderController controller = new ConsoleOrderController(useCase);

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("=== Specialty Coffee Roasters ===");
        System.out.println("Granos disponibles: Geisha, Bourbon Rosado");

        while (continuar) {
            System.out.println("\n--- Nuevo pedido ---");

            System.out.print("Tipo de grano: ");
            String tipoGrano = scanner.nextLine();

            System.out.print("Cantidad en gramos: ");
            int cantidadGramos;
            try {
                cantidadGramos = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ La cantidad debe ser un número válido. Intenta de nuevo.");
                continue;
            }

            System.out.print("Método de preparación (ESPRESSO, V60, PRENSA_FRANCESA, AEROPRESS, CHEMEX): ");
            String metodoTexto = scanner.nextLine().toUpperCase().trim();
            MetodoPreparacion metodoPreparacion;
            try {
                metodoPreparacion = MetodoPreparacion.valueOf(metodoTexto);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Método de preparación inválido: \"" + metodoTexto + "\". Intenta de nuevo.");
                continue;
            }

            controller.procesarPedido(tipoGrano, cantidadGramos, metodoPreparacion);

            System.out.print("\n¿Otro pedido? (s/n): ");
            String respuesta = scanner.nextLine();
            continuar = respuesta.equalsIgnoreCase("s");
        }

        System.out.println("¡Gracias por usar el sistema!");
        scanner.close();
    }
}