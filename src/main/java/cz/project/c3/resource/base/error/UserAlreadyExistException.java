package cz.project.c3.resource.base.error;

public final class UserAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = -530903800821712717L;

    public UserAlreadyExistException() {
        super();
    }

    public UserAlreadyExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistException(final String message) {
        super(message);
    }

    public UserAlreadyExistException(final Throwable cause) {
        super(cause);
    }
}
