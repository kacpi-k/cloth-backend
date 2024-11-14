package dev.kkoncki.cloth.user.management;

public class UserMapper {

    public static User toUser(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .gender(userEntity.getGender())
                .createdOn(userEntity.getCreatedOn())
                .build();
    }
}
