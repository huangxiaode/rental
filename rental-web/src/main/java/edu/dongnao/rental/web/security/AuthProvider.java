package edu.dongnao.rental.web.security;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.dongnao.rental.lang.ServiceResult;
import edu.dongnao.rental.uc.api.IUserService;
import edu.dongnao.rental.uc.domian.UserInfo;

/**
 * 自定义认证实现
 * 
 */
public class AuthProvider implements AuthenticationProvider {
    @Reference
    private IUserService userService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String inputPassword = (String) authentication.getCredentials();

        ServiceResult<UserInfo> userResult = userService.findUserByUserName(userName);
        UserInfo user = userResult.getResult();
        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("authError");
        }

        if (this.passwordEncoder.matches(inputPassword, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        }

        throw new BadCredentialsException("authError");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
