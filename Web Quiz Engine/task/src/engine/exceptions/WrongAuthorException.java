package engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class WrongAuthorException extends RuntimeException {

    public WrongAuthorException() {
        super("You are not the author of this quiz!");
    }

    public WrongAuthorException(String message) {
        super(message);
    }

}
