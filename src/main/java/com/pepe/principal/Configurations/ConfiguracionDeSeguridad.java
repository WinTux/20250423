package com.pepe.principal.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import com.pepe.principal.Filter.ControlConexionFilter;

import jakarta.servlet.Filter;

@Configuration
//@EnableWebSecurity
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
}
