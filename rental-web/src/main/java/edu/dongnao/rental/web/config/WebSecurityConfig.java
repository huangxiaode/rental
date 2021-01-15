package edu.dongnao.rental.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import edu.dongnao.rental.web.security.AuthFilter;
import edu.dongnao.rental.web.security.AuthProvider;
import edu.dongnao.rental.web.security.LoginAuthFailHandler;
import edu.dongnao.rental.web.security.LoginSuccessHandler;
import edu.dongnao.rental.web.security.LoginUrlEntryPoint;

/**
 * 
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * HTTP权限控制
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// 身份验证过滤器
        http.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);

        // 资源访问权限
        http.authorizeRequests()		// 通过URL拦截表达式注册器，注册需要管理的资源权限
                .antMatchers("/admin/login").permitAll() // 管理员登录入口
                .antMatchers("/static/**").permitAll() // 静态资源
                .antMatchers("/user/login").permitAll() // 用户登录入口
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/user/**").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()		// 进行登录配置
                .loginProcessingUrl("/login") // 配置角色登录处理入口
                .failureHandler(authFailHandler())		// 等失败处理
                .successHandler(successHandler())
                .and()
                .logout()			// 登出操作
                .logoutUrl("/logout")		// 登出路径
                .logoutSuccessUrl("/logout/page")		// 登出后回到的页面，也可以是登录页面
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling()		// 异常
                .authenticationEntryPoint(urlEntryPoint())
                .accessDeniedPage("/403");
        // CSRF（Cross-site request forgery）指跨站请求伪造，security4.0默认启用
        // 在请求接口的时候需要加入csrfToken才能正常访问，为了开发方便，禁用它。
        http.csrf().disable();
        // 前端h-ui界面使用iframe开发，设置使用同源策略
        http.headers().frameOptions().sameOrigin();
    }

    /**
     * 自定义认证策略
     */
    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider()).eraseCredentials(true);
    }

    
    @Bean
    public AuthProvider authProvider() {
        return new AuthProvider();
    }

    @Bean
    public LoginUrlEntryPoint urlEntryPoint() {
        return new LoginUrlEntryPoint("/user/login");
    }

    @Bean
    public LoginAuthFailHandler authFailHandler() {
    	return new LoginAuthFailHandler(urlEntryPoint());
    }
    
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new LoginSuccessHandler();
    }
    

    @Bean
    public AuthenticationManager authenticationManager() {
        AuthenticationManager authenticationManager = null;
        try {
            authenticationManager =  super.authenticationManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticationManager;
    }

    @Bean
    public AuthFilter authFilter() {
        AuthFilter authFilter = new AuthFilter();
        authFilter.setAuthenticationManager(authenticationManager());
        authFilter.setAuthenticationFailureHandler(authFailHandler());
        return authFilter;
    }
}
