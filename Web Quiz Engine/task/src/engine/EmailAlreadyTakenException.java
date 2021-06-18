package engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmailAlreadyTakenException extends RuntimeException {

    public EmailAlreadyTakenException() {
        super("Specified email has already been taken!");
    }

    public EmailAlreadyTakenException(String message) {
        super(message);
    }

}
