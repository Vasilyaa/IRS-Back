package net.redciscso.javapmproject.exception;

public class UserNotFoundException extends RuntimeException {

    public static final String cliche = "User not found by %s";

    public UserNotFoundException(String message) {
        super( String.format( cliche , message ) );
    }

    public static final String cliche2  = "User not found by id = %d , userName = %s";


    public UserNotFoundException(Long id,String userName) {
        super( String.format( cliche2 , id ,userName ) );
    }


}

