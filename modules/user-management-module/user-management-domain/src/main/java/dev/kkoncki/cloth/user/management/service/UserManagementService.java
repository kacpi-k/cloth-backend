package dev.kkoncki.cloth.user.management.service;

import dev.kkoncki.cloth.user.management.User;
import dev.kkoncki.cloth.user.management.forms.CreateUserForm;
import dev.kkoncki.cloth.user.management.forms.EditUserForm;
import jakarta.validation.Valid;

public interface UserManagementService {
    User get(String id);
    User save(@Valid CreateUserForm form);
    User update(@Valid EditUserForm form);
    User saveSysAdminOnStartup(@Valid CreateUserForm form);
}
