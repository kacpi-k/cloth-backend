package dev.kkoncki.cloth.user.management.service;

import dev.kkoncki.cloth.user.management.User;
import dev.kkoncki.cloth.user.management.forms.CreateUserForm;
import dev.kkoncki.cloth.user.management.forms.EditUserForm;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {



    @Override
    public User get(String id) {
        return null;
    }

    @Override
    public User save(CreateUserForm form) {
        return null;
    }

    @Override
    public User update(EditUserForm form) {
        return null;
    }

    @Override
    public User createSysAdminOnStartup(CreateUserForm form) {
        return null;
    }
}
