package mk.ukim.finki.lab2emt.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AvailableCopiesException extends RuntimeException {
    public AvailableCopiesException(Integer copies) {
        super(String.format("Book with id: %d is not found", copies));
    }
}
