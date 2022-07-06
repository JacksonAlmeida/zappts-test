package com.sunflower.zappts.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sunflower.zappts.dto.PlayerDTO;
import com.sunflower.zappts.entities.Player;
import com.sunflower.zappts.repositories.PlayerRepository;
import com.sunflower.zappts.services.exceptions.DatabaseException;
import com.sunflower.zappts.services.exceptions.ResourceNotFoundException;

@Service
public class PlayerService implements UserDetailsService {

	@Autowired
	private PlayerRepository playerRepository;
	
	@Autowired
	private PasswordEncoder encoder;

	public Player insert(Player player) {
		player.setPassword(encoder.encode(player.getPassword()));
		return playerRepository.save(player);
	}

	public List<PlayerDTO> findAll() {
		List<Player> obj = playerRepository.findAll();
		return obj.stream().map(x -> new PlayerDTO(x)).collect(Collectors.toList());
	}

	public PlayerDTO findById(long id) {
		Optional<Player> obj = playerRepository.findById(id);
		if(obj.isPresent()) {
			return obj.stream().map(x -> new PlayerDTO()).findAny().get();
		} else {
			Optional<PlayerDTO> dto = Optional.empty();
			return dto.orElseThrow(() -> new ResourceNotFoundException(id));
		}
	}

	public void delete(long id) {
		try {
			playerRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Player update(long id, Player obj) {
		try {
			Player entity = playerRepository.getOne(id);
			updateData(entity, obj);
			return playerRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Player entity, Player obj) {
		entity.setName(obj.getName());
		entity.setPassword(obj.getPassword());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Player> p1 = playerRepository.findByName(username);
		if (p1.isPresent()) {
			return p1.get();
		} else
			throw new UsernameNotFoundException("Dados inválidos");
	}
}