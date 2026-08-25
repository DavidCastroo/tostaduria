package tostaduria.domain.exceptions;

public class GranoNoEncontradoException extends RuntimeException {

    public GranoNoEncontradoException(String tipoGrano) {
        super("No se encontró el grano de café: " + tipoGrano);
    }
}