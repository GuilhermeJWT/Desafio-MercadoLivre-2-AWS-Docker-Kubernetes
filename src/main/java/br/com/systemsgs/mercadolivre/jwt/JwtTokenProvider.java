package br.com.systemsgs.mercadolivre.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.systemsgs.mercadolivre.exception.InvalidJwtAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenProvider {
	
	@Value("${security.jwt.token.secret-key:sec}")
	private String senhaSecreta = "senhasecretamercadolivre";
	
	@Value("${security.jwt.token.tempo.expiracao:3600000}")
	private long tempoExpiracao = 3600000;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@PostConstruct
	public void init() {
		senhaSecreta = Base64.getEncoder().encodeToString(senhaSecreta.getBytes());
	}
	
	public String createToken(String login, List<String> roles) {
		Claims claims = Jwts.claims().setSubject(login);
		claims.put("roles", roles);
		
		Date dataHoje = new Date();
		Date validade = new Date(dataHoje.getTime() + tempoExpiracao);
		
		return Jwts.builder().setClaims(claims).setIssuedAt(dataHoje).setExpiration(validade).signWith(SignatureAlgorithm.HS256, senhaSecreta).compact();
	}
	
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsuario(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "" ,userDetails.getAuthorities());
	}

	private String getUsuario(String token) {
		return Jwts.parser().setSigningKey(senhaSecreta).parseClaimsJws(token).getBody().getSubject();
	}
	
	public String resolveToken(HttpServletRequest request) {
		
		String bearerToken = request.getHeader("Authorization");
		
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		
		return null;
	}
	
	public boolean validateToken(String token) {
		try {
			
		  Jws<Claims> claims = Jwts.parser().setSigningKey(senhaSecreta).parseClaimsJws(token);
	
		  if(claims.getBody().getExpiration().before(new Date())) {
			  return false;
		  }
		  
		  return true;
		}catch (Exception exception) {
			throw new InvalidJwtAuthenticationException("Token Expirado!!!");
		}
	}

}
