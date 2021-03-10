package Exception;

/**
 * token过期或无效异常
 */
public class TokenException extends Exception {
    public TokenException() {
    }

    public TokenException(String message) {
        super(message);
    }
}
