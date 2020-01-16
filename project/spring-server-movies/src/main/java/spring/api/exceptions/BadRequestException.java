package spring.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends ApiException {
    public BadRequestException(String msg) {
        super(HttpStatusCodes.BAD_REQUEST, msg);
    }
}
