package academy.pocu.comp2500.lab11;

public class OverflowException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public OverflowException(final String message) {
        super(message);
    }
}
