package dev.kkoncki.cloth.user.management;

import dev.kkoncki.cloth.user.management.forms.CreateUserForm;
import dev.kkoncki.cloth.user.management.forms.EditUserForm;
import dev.kkoncki.cloth.user.management.service.UserManagementService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user-management")
public class UserManagementController {

    private final UserManagementService userManagementService;

    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") String id) {
        return userManagementService.get(id);
    }

    @PostMapping("/save")
    public User save(@RequestBody CreateUserForm form) {
        return userManagementService.save(form);
    }

    @PutMapping("/update")
    public User update(@RequestBody EditUserForm form) {
        return userManagementService.update(form);
    }
}
