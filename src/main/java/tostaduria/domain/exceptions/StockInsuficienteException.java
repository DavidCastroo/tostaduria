package tostaduria.domain.exceptions;

public class StockInsuficienteException extends RuntimeException {

    public StockInsuficienteException(String tipoGrano, int cantidadSolicitada, int cantidadDisponible) {
        super("No hay suficiente stock de " + tipoGrano +
              ". Solicitado: " + cantidadSolicitada + "g, disponible: " + cantidadDisponible + "g");
    }
}