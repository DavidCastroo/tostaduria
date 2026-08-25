package tostaduria.domain.entities;

import tostaduria.domain.exceptions.StockInsuficienteException;

public class CoffeeBean {

    // private = encapsulamiento. Nadie desde afuera puede modificar
    // estos valores directamente, solo a través de los métodos que definamos.
    private final String tipo;
    private int cantidadDisponibleGramos;

    // Constructor: así se crea un CoffeeBean válido.
    // Ambos datos son obligatorios porque un grano sin tipo o sin
    // cantidad inicial no tendría sentido en el dominio.
    public CoffeeBean(String tipo, int cantidadDisponibleGramos) {
        this.tipo = tipo;
        this.cantidadDisponibleGramos = cantidadDisponibleGramos;
    }

    // Solo exponemos "lectura" del tipo, no se puede cambiar después de creado
    // (por eso también es 'final' arriba).
    public String getTipo() {
        return tipo;
    }

    public int getCantidadDisponibleGramos() {
        return cantidadDisponibleGramos;
    }

    // Regla de negocio: ¿alcanza el stock para lo que se pide?
    public boolean hayStockSuficiente(int cantidadSolicitada) {
        return this.cantidadDisponibleGramos >= cantidadSolicitada;
    }

    // Regla de negocio: descontar stock, validando la invariante
    // (nunca puede quedar en negativo). Si no alcanza, lanza la excepción
    // de dominio en vez de silenciosamente dejar un número negativo.
    public void descontarStock(int cantidad) {
        if (!hayStockSuficiente(cantidad)) {
            throw new StockInsuficienteException(this.tipo, cantidad, this.cantidadDisponibleGramos);
        }
        this.cantidadDisponibleGramos -= cantidad;
    }
}