package spring.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends ApiException {
    public AuthenticationException(String msg) {
        super(HttpStatusCodes.UNAUTHORIZED, msg);
    }
}
