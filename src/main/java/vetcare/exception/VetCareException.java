package vetcare.exception;

public abstract class VetCareException extends RuntimeException {
    
    public VetCareException(String mensaje) {
        super(mensaje);
    }
}