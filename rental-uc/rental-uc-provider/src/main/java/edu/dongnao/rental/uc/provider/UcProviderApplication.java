package edu.dongnao.rental.uc.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
@EnableDubbo
public class UcProviderApplication {
	
	public static void main(String[] args) {
		// 非web容器方式启动
		new SpringApplicationBuilder(UcProviderApplication.class)
        .run(args);
	}
}
