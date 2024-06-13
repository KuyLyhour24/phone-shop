package com.lyhour.java.study.phone_shop.config.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .antMatchers("/","index.html",".css/**").permitAll()
                .antMatchers("/brands").hasRole("SALE")
                .anyRequest()
                .authenticated())
                .httpBasic(withDefaults());
	}
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
				.username("dara")
				.password(passwordEncoder.encode("dara123"))
				.roles("SALE")
				.build();
		UserDetails user1 = User.builder()
				.username("thida")
				.password(passwordEncoder.encode("thida123"))
				.roles("ADMIN")
				.build();
		UserDetailsService userDetailsService = new InMemoryUserDetailsManager(user,user1);
		return userDetailsService;
	}

}
