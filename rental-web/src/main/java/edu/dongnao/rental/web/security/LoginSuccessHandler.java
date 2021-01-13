package edu.dongnao.rental.web.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * LoginSuccessHandler
 * 
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Override
	@SuppressWarnings("unchecked")
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String uri = request.getRequestURI().replace(request.getContextPath(), "");
		if("/admin/login".equals(uri)) {
			List<GrantedAuthority> grantedAuthority = (List<GrantedAuthority>) authentication.getAuthorities();
			grantedAuthority.forEach(g -> {
				if("ADMIN".equals(g.getAuthority())) {
					try {
						response.sendRedirect("/admin/center");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}
		
	}

}

