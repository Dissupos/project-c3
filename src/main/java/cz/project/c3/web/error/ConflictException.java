package cz.project.c3.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public final class ConflictException extends RuntimeException {

    private static final long serialVersionUID = -530903800821712717L;

    public ConflictException() {
        super();
    }

    public ConflictException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ConflictException(final String message) {
        super(message);
    }

    public ConflictException(final Throwable cause) {
        super(cause);
    }
}
