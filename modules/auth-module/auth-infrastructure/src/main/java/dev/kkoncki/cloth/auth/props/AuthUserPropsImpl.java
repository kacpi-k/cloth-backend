package dev.kkoncki.cloth.auth.props;

import dev.kkoncki.cloth.auth.interfaces.AuthUserProps;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AuthUserPropsImpl implements AuthUserProps {

    private final Environment env;

    public AuthUserPropsImpl(Environment env) {
        this.env = env;
    }

    @Override
    public String getSysAdminFirstName() {
        return env.getRequiredProperty("app.auth.sysAdminFirstName");
    }

    @Override
    public String getSysAdminLastName() {
        return env.getRequiredProperty("app.auth.sysAdminLastName");
    }

    @Override
    public String getSysAdminEmail() {
        return env.getRequiredProperty("app.auth.sysAdminEmail");
    }

    @Override
    public String getSysAdminPassword() {
        return env.getRequiredProperty("app.auth.sysAdminPassword");
    }
}
