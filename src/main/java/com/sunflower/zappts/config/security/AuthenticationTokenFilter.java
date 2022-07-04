package com.sunflower.zappts.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sunflower.zappts.entities.Player;
import com.sunflower.zappts.repositories.PlayerRepository;
import com.sunflower.zappts.services.TokenService;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;

	private PlayerRepository playerRepository;

	public AuthenticationTokenFilter(TokenService tokenService, PlayerRepository playerRepository) {
		this.tokenService = tokenService;
		this.playerRepository = playerRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = findToken(request);

		boolean valid = tokenService.isTokenValid(token);
		if (valid) {
			authenticateclient(token);
		}

		filterChain.doFilter(request, response);

	}

	private void authenticateclient(String token) {
		long idUser = tokenService.getIdUser(token);
		Optional<Player> player = playerRepository.findById(idUser);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(player, null,
				player.get().getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String findToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}
