package ch.heig.amt.pokemon.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PokemonBadRequestException extends RuntimeException {
    public PokemonBadRequestException(String exception) {
        super(exception);
    }
}
