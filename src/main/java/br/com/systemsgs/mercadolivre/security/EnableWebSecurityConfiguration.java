package br.com.systemsgs.mercadolivre.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.systemsgs.mercadolivre.jwt.JwtTokenConfigurer;
import br.com.systemsgs.mercadolivre.jwt.JwtTokenProvider;

@EnableWebSecurity
@Configuration
public class EnableWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
		.antMatchers("/api/usuario/**").authenticated()
		.antMatchers("/api/categoria/**").authenticated()
		.antMatchers("/api/auth/**").permitAll()
		.and().apply(new JwtTokenConfigurer(tokenProvider));
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
	
	@Bean
	public AuthenticationManager authenticationManaherBean() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
