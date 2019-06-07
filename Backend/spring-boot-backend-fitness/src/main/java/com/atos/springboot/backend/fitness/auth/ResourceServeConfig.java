package com.atos.springboot.backend.fitness.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServeConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		

		http.authorizeRequests()
		.antMatchers("/users/register").permitAll()
		.antMatchers("/alimentos/insertarAlimentos/{id_usuario}/{id_alimento}").permitAll()
		.antMatchers("/objetivos/insertarId/{altura}/{edad}/{nivel}/{peso}/{sexo}/{usuario}").permitAll()
		.antMatchers("/objetivos/registerObjetivo").permitAll()
		.anyRequest().authenticated()
		.antMatchers("/users/usuarios").hasRole("ROL_ADMIN")
		.and().cors().configurationSource(corsConfigurationSource()); // le ponemos las rutas que queremos que
																				// el cliente pueda accedes

	}

	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration config = new CorsConfiguration();

		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		source.registerCorsConfiguration("/**", config);

		return source;

	}

	// Creamos un filtro para pasarle toda la configuracion
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {

		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(
				new CorsFilter(corsConfigurationSource()));

		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

		return bean;

	}

}
