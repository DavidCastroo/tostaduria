package tostaduria.application.ports.out;

import tostaduria.domain.entities.CoffeeBean;

public interface InventoryPort {
    CoffeeBean buscarPorTipo(String tipoGrano);
    void actualizarInventario(CoffeeBean coffeeBean);
}