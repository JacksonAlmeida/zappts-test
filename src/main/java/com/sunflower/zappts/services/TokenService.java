package com.sunflower.zappts.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.sunflower.zappts.entities.Player;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${jwt.expiration}")
	private Long expiration;
	
	@Value("${jwt.secret}")
	private String key;
	
	public String generateToken(Authentication authentication) {
		Player player = (Player) authentication.getPrincipal();
		return Jwts.builder()
				.setIssuer("Teste Zappts :)")
				.setSubject(Long.toString(player.getId()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, key)
				.compact();
	}

	public boolean isTokenValid(String token) {

		try {
			Jwts.parser().setSigningKey(this.key).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUser(String token) {
		Claims body = Jwts.parser().setSigningKey(this.key).parseClaimsJws(token).getBody();
		return Long.parseLong(body.getSubject());
	}
}
