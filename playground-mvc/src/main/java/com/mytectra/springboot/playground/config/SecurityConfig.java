package com.mytectra.springboot.playground.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/*@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true, jsr250Enabled = true)*/
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetails;
		
	@Override
	protected UserDetailsService userDetailsService() {
		return userDetails;
	}
	protected void configure(HttpSecurity http) {
		// Always put basic authentication (.httpBasic) at the end, as we are using basic authentication over here.
		// Otherwise, application will consider the web authentication (username & password).
		try {
			http
			.userDetailsService(userDetails)
				.authorizeRequests()
				.antMatchers("/swagger-ui.html" , "/webjars/**" , "/swagger-resources/**" , "/v2/api-docs/**")
				.anonymous()
			.and()
				.authorizeRequests()
				.antMatchers("/**")
				.authenticated()
			.and()
				.httpBasic()
			.and()
				.csrf()
				.disable();
			
			//disabled csrf due to postman not getting the X_CSRF token cookie
			//http.csrf().disable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
