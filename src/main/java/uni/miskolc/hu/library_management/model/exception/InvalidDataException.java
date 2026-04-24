package uni.miskolc.hu.library_management.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidDataException extends RuntimeException {
    
    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException() {
        super("Érvénytelen adatokat adtál meg!");
    }
}