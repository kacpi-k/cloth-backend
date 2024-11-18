package dev.kkoncki.cloth.auth.security;

import dev.kkoncki.cloth.auth.interfaces.TokenProvider;
import dev.kkoncki.cloth.security.JwtUtil;
import org.springframework.stereotype.Component;

@Component
public class TokenProviderAdapter implements TokenProvider {

    private final JwtUtil jwtUtil;

    public TokenProviderAdapter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String generateToken(String userId) {
        return jwtUtil.generateToken(userId);
    }
}
