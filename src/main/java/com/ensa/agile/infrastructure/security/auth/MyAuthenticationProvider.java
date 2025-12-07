package com.ensa.agile.infrastructure.security.auth;

import java.util.List;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ensa.agile.application.user.usecase.LoadUserInfoUseCase;
import com.ensa.agile.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    private final LoadUserInfoUseCase userInfoUseCase;
    private final PasswordEncoder passwordEncoder;

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        User u = userInfoUseCase.execute(email);
        String rawPassword = authentication.getCredentials().toString();

        if (!passwordEncoder.matches(rawPassword, u.getPassword())) {
            return null;
        }
        List<GrantedAuthority> authorities = u.getAuthorities()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(u, null, authorities);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
