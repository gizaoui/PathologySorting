package fr.softwaymedical.exception;

public class HealthIdException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public HealthIdException(String errorMessage) {
        super(errorMessage);
    }

    public HealthIdException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
