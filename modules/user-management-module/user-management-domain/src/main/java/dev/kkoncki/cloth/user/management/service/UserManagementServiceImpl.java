package dev.kkoncki.cloth.user.management.service;

import dev.kkoncki.cloth.commons.ApplicationException;
import dev.kkoncki.cloth.commons.ErrorCode;
import dev.kkoncki.cloth.user.management.User;
import dev.kkoncki.cloth.user.management.forms.CreateUserForm;
import dev.kkoncki.cloth.user.management.forms.EditUserForm;
import dev.kkoncki.cloth.user.management.repository.UserManagementRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    private final UserManagementRepository userManagementRepository;

    public UserManagementServiceImpl(UserManagementRepository userManagementRepository) {
        this.userManagementRepository = userManagementRepository;
    }

    @Override
    public User get(String id) {
        return userManagementRepository.get(id).orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public User save(CreateUserForm form) {
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .gender(form.getGender())
                .createdOn(Instant.now())
                .favoriteProductsIds(new ArrayList<>())
                .build();
        return userManagementRepository.save(user);
    }

    @Override
    public User update(EditUserForm form) {
        User user = get(form.getId());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setEmail(form.getEmail());

        return userManagementRepository.save(user);
    }

    @Override
    public User saveSysAdminOnStartup(CreateUserForm form) {

        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .gender(form.getGender())
                .createdOn(Instant.now())
                .build();

        return userManagementRepository.save(user);
    }

    @Override
    public void saveFavoriteProduct(User user) {
        userManagementRepository.save(user);
    }
}
