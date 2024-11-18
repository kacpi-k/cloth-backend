package dev.kkoncki.cloth.auth;

public class AuthUserMapper {

    public static AuthUser toAuthUser(AuthUserEntity authUserEntity) {
        return AuthUser.builder()
                .id(authUserEntity.getId())
                .email(authUserEntity.getEmail())
                .password(authUserEntity.getPassword())
                .build();
    }
}
