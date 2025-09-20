package com.blog_app_apis.config;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.blog_app_apis.security.CustomeUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	@Autowired
	private CustomeUserDetailsService customeUserDetailsService;
	 

//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http
//		.authorizeHttpRequests(auth-> auth
//		.anyRequest()
//		.authenticated())
//		.httpBasic(withDefaults());
//		return http.build();
//		
//	}

//	           InMemory Authentication 

//	   @Bean
//	    public UserDetailsService userDetailsService() {
//	        UserDetails user1 = User.withUsername("user")
//	                .password("{noop}password") // {noop} means no encoding
//	                .roles("USER")
//	                .build();
//
//	        UserDetails admin = User.withUsername("admin")
//	                .password("{noop}admin123")
//	                .roles("ADMIN")
//	                .build();
//
//	        return new InMemoryUserDetailsManager(user1, admin);
//	    }

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/error").permitAll()      // ✅ allow error page
            .requestMatchers("/public/**").permitAll()  // example for open APIs
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
            .anyRequest().authenticated()
        )
        .httpBasic(withDefaults());
    return http.build();

	}

	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public DaoAuthenticationProvider daoAuthenticationProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.customeUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
