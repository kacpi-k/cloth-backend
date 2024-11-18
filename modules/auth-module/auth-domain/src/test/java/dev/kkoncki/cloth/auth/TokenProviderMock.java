package dev.kkoncki.cloth.auth;

import dev.kkoncki.cloth.auth.interfaces.TokenProvider;

import java.util.UUID;

public class TokenProviderMock implements TokenProvider {
    @Override
    public String generateToken(String userId) {
        return UUID.randomUUID().toString();
    }
}
