package dev.kkoncki.cloth.auth.interfaces;

public interface PasswordEncoder {
    String encode(String rawText);
    boolean match(String rawText, String encoded);
}
