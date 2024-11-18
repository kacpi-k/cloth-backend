package dev.kkoncki.cloth.auth;

import dev.kkoncki.cloth.auth.interfaces.AuthUserProps;

public class AuthUserPropsMock implements AuthUserProps {
    @Override
    public String getSysAdminFirstName() {
        return "admin";
    }

    @Override
    public String getSysAdminLastName() {
        return "admin";
    }

    @Override
    public String getSysAdminEmail() {
        return "admin@test.pl";
    }

    @Override
    public String getSysAdminPassword() {
        return "password123";
    }
}
