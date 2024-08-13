package com.palmen.foodtracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.palmen.foodtracker.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
				.authorizeHttpRequests(authorizeRequests -> authorizeRequests
						.requestMatchers("/about", "/registro", "/registrarUsuario", "/listarAlimentos",
								"/enviarCodigo", "/analizadorAlimentos", "/filtrarAlimentos", "/css/**", "/images/**")
						.permitAll().anyRequest().authenticated())
				.formLogin(formLogin -> formLogin.loginPage("/login").loginProcessingUrl("/login")
						.usernameParameter("nombreUsuario").passwordParameter("password")
						.defaultSuccessUrl("/listarAlimentos", true).permitAll())
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/about?logout")
						.invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll());
				//.csrf(csrf -> csrf.ignoringRequestMatchers("/enviarCodigo", "/listarAlimentos")); // Ignorar CSRF para
																									// esta ruta de
																									// manera momentanea
		return http.build();
	}

	@Bean
	UserDetailsService userDetailsService() {
		return customUserDetailsService;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();

	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());

		return provider;
	}

}
