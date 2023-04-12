package net.redciscso.javapmproject.exception;


public class UserNameAlreadyExistException extends RuntimeException {

    public static final String cliche = "Username is busy %s";

    public UserNameAlreadyExistException(String message) {
        super(String.format(cliche, message));
    }
}

