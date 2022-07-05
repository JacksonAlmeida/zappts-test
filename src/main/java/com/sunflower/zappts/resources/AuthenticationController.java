package com.sunflower.zappts.resources;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunflower.zappts.dto.LoginDTO;
import com.sunflower.zappts.dto.TokenDTO;
import com.sunflower.zappts.entities.Player;
import com.sunflower.zappts.services.TokenService;

@RestController
@RequestMapping(value = "/api/auth", produces = "application/json")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping(value = "/login")
	public ResponseEntity<TokenDTO> authenticate(@RequestBody LoginDTO obj) {

		Player p1 = new Player();

		BeanUtils.copyProperties(obj, p1);

		UsernamePasswordAuthenticationToken dadosLogin = p1.converter();

		try {
			Authentication authentication = authenticationManager.authenticate(dadosLogin);
			String token = tokenService.generateToken(authentication);
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
