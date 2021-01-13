package edu.dongnao.rental.web.security;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.google.common.base.Strings;

import edu.dongnao.rental.lang.ServiceResult;
import edu.dongnao.rental.uc.api.ISmsService;
import edu.dongnao.rental.uc.api.IUserService;
import edu.dongnao.rental.uc.domian.UserInfo;
import edu.dongnao.rental.web.base.LoginUserUtil;

/**
 * 通过用户名和密码进行身份验证
 * 
 *
 */
public class AuthFilter extends UsernamePasswordAuthenticationFilter {
	@Reference
	IUserService userService;
	
	@Reference
	ISmsService smsService;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String name = obtainUsername(request);
		if(! Strings.isNullOrEmpty(name)) {
			request.setAttribute("username", name);
			return super.attemptAuthentication(request, response);
		}
		
		String telephone = request.getParameter("telephone");
		 if (Strings.isNullOrEmpty(telephone) || !LoginUserUtil.checkTelephone(telephone)) {
	            throw new BadCredentialsException("Wrong telephone number");
	     }
		 
		ServiceResult<UserInfo> userResult = userService.findUserByTelephone(telephone);
        UserInfo user = userResult.getResult();
        String inputCode = request.getParameter("smsCode");
        String sessionCode = smsService.getSmsCode(telephone);
        if (Objects.equals(inputCode, sessionCode)) {
        	if(user == null) {
        		ServiceResult<UserInfo> newUserResult = userService.addUserByTelephone(telephone);
        		user = newUserResult.getResult();
        	}
        	return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }
		 
		throw new BadCredentialsException("验证发生错误");
	}

}
