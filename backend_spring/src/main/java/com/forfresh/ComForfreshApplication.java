package com.forfresh;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.forfresh.interceptor.JwtInterceptor;


@SpringBootApplication
public class ComForfreshApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(ComForfreshApplication.class, args);
	}
	
	@Autowired
	private JwtInterceptor jwtInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/account/**") // 기본 적용 경로
				.excludePathPatterns(Arrays.asList("/account/login/**")) // 적용 제외 경로 
				.excludePathPatterns(Arrays.asList("/account/user/join"))
				.excludePathPatterns(Arrays.asList("/account/user/search/nickname"))
				.excludePathPatterns(Arrays.asList("/account/user/update/password"));
		
	}

	/**
	 * Interceptoer를 이용해서 처리하므로 전역의 Cross origin 처리를 해준다
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*")
				.exposedHeaders("jwt-auth-token");
	}
}
