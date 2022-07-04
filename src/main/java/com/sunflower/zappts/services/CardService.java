package com.sunflower.zappts.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.sunflower.zappts.dto.CardDTO;
import com.sunflower.zappts.entities.Card;
import com.sunflower.zappts.repositories.CardRepository;
import com.sunflower.zappts.services.exceptions.DatabaseException;
import com.sunflower.zappts.services.exceptions.ResourceNotFoundException;

@Service
public class CardService {

	@Autowired
	private CardRepository roleRepository;

	public Card insert(Card role) {
		return roleRepository.save(role);
	}

	public List<CardDTO> findAll() {
		List<Card> obj = roleRepository.findAll();
		return obj.stream().map(x -> new CardDTO(x)).collect(Collectors.toList());
	}

	public CardDTO findById(long id) {
		Optional<Card> obj = roleRepository.findById(id);
		if (obj.isPresent()) {
			return obj.stream().map(x -> new CardDTO(x)).findAny().get();
		} else {
			Optional<CardDTO> dto = Optional.empty();
			return dto.orElseThrow(() -> new ResourceNotFoundException(id));
		}
	}

	public void delete(long id) {
		try {
			roleRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Card update(long id, Card obj) {
		try {
			Card entity = roleRepository.getOne(id);
			updateData(entity, obj);
			return roleRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Card entity, Card obj) {
		entity.setName(obj.getName());
	}
}