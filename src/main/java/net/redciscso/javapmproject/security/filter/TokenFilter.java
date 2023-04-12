package net.redciscso.javapmproject.security.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.redciscso.javapmproject.security.exception.BadTokenException;
import net.redciscso.javapmproject.security.token.TokenAuthentication;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
public class TokenFilter implements Filter {

    private final AuthenticationProvider authenticationProvider;

    private static final List<String> urlPatterns = Arrays.asList(
            "csrf",
            "actuator",
            "error"
    );

    private static final String IGNORED_URL = "/";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String token = request.getHeader("Authorization");

        if (urlPatterns.contains(request.getRequestURI())) {
            filterChain.doFilter(request, servletResponse);
            return;
        }

        TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
        if (token == null) {
            tokenAuthentication.setAuthenticated(false);
        } else {
            try {
                TokenAuthentication auth = (TokenAuthentication) authenticationProvider.authenticate(tokenAuthentication);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (BadTokenException e) {
                log.info("Bad token : [{}]", token);
                HttpServletResponse resp = (HttpServletResponse) servletResponse;
                resp.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


}

