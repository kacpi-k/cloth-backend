package dev.kkoncki.cloth.auth.forms;

import dev.kkoncki.cloth.user.management.forms.CreateUserForm;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserWithPasswordForm extends CreateUserForm {

    @NotBlank(message = "Password must not be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
