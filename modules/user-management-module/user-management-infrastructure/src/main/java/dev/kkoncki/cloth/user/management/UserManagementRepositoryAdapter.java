package dev.kkoncki.cloth.user.management;

import dev.kkoncki.cloth.user.management.repository.UserManagementRepository;

import java.util.Optional;

public class UserManagementRepositoryAdapter implements UserManagementRepository {

    private final JpaUserManagementRepository userManagementRepository;

    public UserManagementRepositoryAdapter(JpaUserManagementRepository userManagementRepository) {
        this.userManagementRepository = userManagementRepository;
    }

    @Override
    public Optional<User> get(String id) {
        Optional<UserEntity> userEntity = userManagementRepository.findById(id);
        return userEntity.map(UserMapper::toUser);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userManagementRepository.save(new UserEntity(user));
        return UserMapper.toUser(userEntity);
    }
}
