package net.redciscso.javapmproject.security.exception;

public class BadTokenException extends RuntimeException {


    public static final String cliche = "Bad token";

    public BadTokenException() {
        super(cliche);
    }

}

