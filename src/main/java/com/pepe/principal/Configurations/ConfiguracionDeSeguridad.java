package com.pepe.principal.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.pepe.principal.Filter.ControlConexionFilter;

import jakarta.servlet.Filter;

@Configuration
@EnableWebSecurity
public class ConfiguracionDeSeguridad {
	@Autowired
	ControlConexionFilter filtro;
	@Bean
	FilterRegistrationBean<Filter> miFiltro(){
		FilterRegistrationBean<Filter> f = 
			new FilterRegistrationBean<Filter>();
		f.setFilter(filtro);
		f.addUrlPatterns("/estudiante/*");
		f.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return f;
	};
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http
				.securityMatcher(EndpointRequest.toAnyEndpoint())
				.authorizeHttpRequests(
						authorize -> 
						authorize
						  .anyRequest()
						  .permitAll())
				.build();
	}
}
