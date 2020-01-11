package spring.api.exceptions;

public class NotAuthenticatedException extends ApiException {
    private int code;

    public NotAuthenticatedException (int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
