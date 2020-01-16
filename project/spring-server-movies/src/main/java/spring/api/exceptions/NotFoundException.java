package spring.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static spring.api.exceptions.HttpStatusCodes.NOT_FOUND;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends ApiException {
    public NotFoundException (String msg) {
        super(NOT_FOUND, msg);
    }
}
