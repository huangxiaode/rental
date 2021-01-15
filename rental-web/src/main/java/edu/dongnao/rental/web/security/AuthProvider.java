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
 * 认证方式提供实现类，我们这采用用户名密码的方式进行验证。
 * 
 */
public class AuthProvider implements AuthenticationProvider {
    @Reference
    private IUserService userService;
    
    // MD5加密存在暴力破解、彩虹表的问题，使用bcrypt加密，也采用hash函数加密，但每次加密后的结果都不同，因此更安全。
    // 每次加密通过加不同的盐来产生不同的加密值，salt值、hash后的值都存放在加密串中
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 先获得输入的用户名和凭证（密码）
    	String userName = authentication.getName();
        String inputPassword = (String) authentication.getCredentials();
        
        // 通过用户名和密码进行身份验证
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
