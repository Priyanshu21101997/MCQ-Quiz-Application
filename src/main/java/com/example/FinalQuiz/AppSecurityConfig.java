package com.example.FinalQuiz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;






@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{

//	// To have our own username and password from inmemory
//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() {
//		
//		List<UserDetails> users = new ArrayList<>();
//		users.add(User.withDefaultPasswordEncoder().username("priyanshu").password("123456").roles("USER").build());  // Deprecated
//		//users.add(User.withDefaultPasswordEncoder().username("khullar").password("123456").roles("USER").build());  // Can add multiple users this way
//		return new InMemoryUserDetailsManager(users);  // Since we are not using any DB we are using in memory DB here thatswhy this command
//		
//	}
	
	// Make use of username and password from DB for authentication
	
	

//	@Autowired
//	private UserDetailsService UserDetailsService;
//	
	@Autowired
	AuthenticationSuccessHandler successHandler;
//	@Bean
//	public AuthenticationProvider authProvider() {
//		
//		
//		
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(UserDetailsService);
//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // Plain text as password no encoding
//		return provider;
//	}
//	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
		.csrf().disable().authorizeRequests()
		.antMatchers("/new","/").hasAnyRole("ADMIN")
			.antMatchers("/index.html").hasAnyRole("USER")			
			.and().formLogin()
			.successHandler(successHandler).permitAll();
		
//		http
//		.csrf().disable()
//		.authorizeRequests()
//		.antMatchers("/index.html").hasAnyRole("USER")
//		.antMatchers("/addQuestion.html","/insertQuestion").hasAnyRole("ADMIN")
//		.antMatchers("/").permitAll()
//		.and().formLogin()
//			.successHandler(successHandler)
//		.permitAll();
		
		

		
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
		.withUser("admin")
		.password("admin")
		.roles("ADMIN")
		.and()
		.withUser("user")
		.password("user")
		.roles("USER");
//		
	}
	

}
