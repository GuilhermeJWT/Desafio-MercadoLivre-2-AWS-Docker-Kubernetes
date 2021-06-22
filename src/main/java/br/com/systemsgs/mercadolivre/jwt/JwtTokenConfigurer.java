package br.com.systemsgs.mercadolivre.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtTokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{

	@Autowired
	private JwtTokenProvider tokenProvider;

	public JwtTokenConfigurer(JwtTokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		JwtTokenFilter filter = new JwtTokenFilter(tokenProvider);
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}
	
}
