package net.redciscso.javapmproject.service.impl;

import lombok.RequiredArgsConstructor;
import net.redciscso.javapmproject.exception.UserNotFoundException;
import net.redciscso.javapmproject.repository.UserRepository;
import net.redciscso.javapmproject.security.details.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsImpl(
                userRepository.findByUsername(username)
                        .orElseThrow(
                                ()-> new UserNotFoundException(username)
                        )
        );
    }
}

