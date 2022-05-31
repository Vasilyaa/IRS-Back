package net.redciscso.javapmproject.util;

public interface StringGenerator {
    String generatePasswordHash(String password);

    String generateToken();

}
