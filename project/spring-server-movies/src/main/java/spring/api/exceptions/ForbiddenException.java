package spring.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class ForbiddenException extends ApiException {
    public ForbiddenException(String msg) {
        super(HttpStatusCodes.FORBIDDEN, msg);
    }
}
