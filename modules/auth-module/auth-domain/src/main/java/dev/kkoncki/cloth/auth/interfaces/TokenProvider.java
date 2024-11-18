package dev.kkoncki.cloth.auth.interfaces;

public interface TokenProvider {
    String generateToken(String userId);
}
