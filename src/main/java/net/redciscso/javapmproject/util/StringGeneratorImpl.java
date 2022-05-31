package net.redciscso.javapmproject.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StringGeneratorImpl implements StringGenerator {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public StringGeneratorImpl(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String generatePasswordHash(String password) {
        return passwordEncoder.encode(password);
    }

    @Value(value = "#{new Integer('${token.length}')}")
    private Integer tokenLength;

    @Override
    public String generateToken() {
        return RandomStringUtils.random(tokenLength,true,true);
    }
}

