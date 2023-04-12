package net.redciscso.javapmproject.security.provider;

import lombok.RequiredArgsConstructor;
import net.redciscso.javapmproject.domain.Token;
import net.redciscso.javapmproject.domain.User;
import net.redciscso.javapmproject.repository.TokenRepository;
import net.redciscso.javapmproject.security.exception.BadTokenException;
import net.redciscso.javapmproject.security.token.TokenAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final TokenRepository tokensRepository;

    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication)authentication;
        Optional<Token> tokenCandidate = tokensRepository.findOneByValue(tokenAuthentication.getName());
        if (tokenCandidate.isPresent()) {
            User user = tokenCandidate.get().getUser();

            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            tokenAuthentication.setUserDetails(userDetails);
            tokenAuthentication.setAuthenticated(true);
            return tokenAuthentication;
        } else throw new BadTokenException();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}

