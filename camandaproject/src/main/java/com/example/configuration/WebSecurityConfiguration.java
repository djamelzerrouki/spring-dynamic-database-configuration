package com.example.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.headers()
				.frameOptions().disable() // Required for H2 console
				.and()
			.csrf()
				.disable() // CSRF protection is not supported by Camunda Webapps
			.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error")
				.and()
			.authorizeRequests()
				.antMatchers("/login", "/register/**").permitAll()
				.antMatchers("/app/**", "/lib/**", "/api/**").permitAll() // Required for Camunda Webapps
				.antMatchers("/h2-console/**").permitAll() // Required for H2 console
				.antMatchers("/img/**", "/css/**").permitAll() // Static resources
				.anyRequest().authenticated();
		// @formatter:on
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// @formatter:off
		auth.userDetailsService(getInMemoryUserDetailsManager());
		// @formatter:on
	}
	
	@Bean
    public InMemoryUserDetailsManager getInMemoryUserDetailsManager() {
		List<GrantedAuthority> adminAuthorities = new ArrayList<GrantedAuthority>();
		adminAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		adminAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		UserDetails admin = new User("admin", "admin", adminAuthorities);
		
		List<GrantedAuthority> userAuthorities = new ArrayList<GrantedAuthority>();
		userAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		UserDetails kermit = new User("kermit", "kermit", userAuthorities);
		UserDetails gonzo = new User("gonzo", "gonzo", userAuthorities);
		
		Collection<UserDetails> users = new ArrayList<>();
		users.add(admin);
		users.add(kermit);
		users.add(gonzo);
		
        return new InMemoryUserDetailsManager(users);
    }

}
