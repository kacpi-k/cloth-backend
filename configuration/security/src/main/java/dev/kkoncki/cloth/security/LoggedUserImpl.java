package dev.kkoncki.cloth.security;

import dev.kkoncki.cloth.commons.ApplicationException;
import dev.kkoncki.cloth.commons.ErrorCode;
import dev.kkoncki.cloth.commons.LoggedUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggedUserImpl implements LoggedUser {
    @Override
    public String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        throw new ApplicationException(ErrorCode.USER_NOT_LOGGED_IN);
    }
}
