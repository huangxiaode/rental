package edu.dongnao.rental.web;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * web层启动入口。
 * <p>
 * 后端采用Dubbo服务实现
 * </p>
 *
 */
@SpringBootApplication
@EnableDubbo
@EnableGlobalMethodSecurity(securedEnabled = true)
public class RentalWebApplication {
	
    public static void main( String[] args ) {
        SpringApplication.run(RentalWebApplication.class, args);
    }
}
