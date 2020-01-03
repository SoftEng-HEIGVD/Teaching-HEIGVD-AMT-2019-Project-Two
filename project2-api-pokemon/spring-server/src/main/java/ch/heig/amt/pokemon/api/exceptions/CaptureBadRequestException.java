package ch.heig.amt.pokemon.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CaptureBadRequestException extends RuntimeException {
    public CaptureBadRequestException(String exception) {
        super(exception);
    }
}
