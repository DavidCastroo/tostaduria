package tostaduria.domain.entities;

import tostaduria.domain.enums.MetodoPreparacion;

public class Order {

    private final String tipoGrano;
    private final int cantidadGramos;
    private final MetodoPreparacion metodoPreparacion;

    public Order(String tipoGrano, int cantidadGramos, MetodoPreparacion metodoPreparacion) {
        this.tipoGrano = tipoGrano;
        this.cantidadGramos = cantidadGramos;
        this.metodoPreparacion = metodoPreparacion;
    }

    public String getTipoGrano() {
        return tipoGrano;
    }

    public int getCantidadGramos() {
        return cantidadGramos;
    }

    public MetodoPreparacion getMetodoPreparacion() {
        return metodoPreparacion;
    }
}