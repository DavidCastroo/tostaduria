package tostaduria.infrastructure.adapters.out.persistence;

import tostaduria.application.ports.out.InventoryPort;
import tostaduria.domain.entities.CoffeeBean;
import tostaduria.domain.exceptions.GranoNoEncontradoException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryInventoryAdapter implements InventoryPort {

    // "Base de datos falsa": guarda CoffeeBean usando su tipo (String) como clave.
    private final Map<String, CoffeeBean> inventario = new HashMap<>();

    // Constructor para poder precargar datos de prueba al crear el adaptador
    // (ej. desde Main.java, para que haya algo que buscar).
    public InMemoryInventoryAdapter() {
        // Datos iniciales de ejemplo — puedes agregar/quitar los que quieras.
        inventario.put("Geisha", new CoffeeBean("Geisha", 500));
        inventario.put("Bourbon Rosado", new CoffeeBean("Bourbon Rosado", 300));
    }

    @Override
    public CoffeeBean buscarPorTipo(String tipoGrano) {
        if (!inventario.containsKey(tipoGrano)) {
            throw new GranoNoEncontradoException(tipoGrano);
        }
        return inventario.get(tipoGrano);
    }

    @Override
    public void actualizarInventario(CoffeeBean coffeeBean) {
        inventario.put(coffeeBean.getTipo(), coffeeBean);
    }
}