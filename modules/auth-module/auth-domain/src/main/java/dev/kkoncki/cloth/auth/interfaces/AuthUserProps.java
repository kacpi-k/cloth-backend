package dev.kkoncki.cloth.auth.interfaces;

public interface AuthUserProps {
    long getResetPasswordTokenExpirationInMinutes();
    String getSysAdminFirstName();
    String getSysAdminLastName();
    String getSysAdminEmail();
    String getSysAdminPassword();
}
