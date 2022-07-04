package com.sunflower.zappts.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sunflower.zappts.dto.CardDTO;
import com.sunflower.zappts.entities.Card;
import com.sunflower.zappts.services.CardService;

@RestController
@RequestMapping(value = "/api/cards", produces = "application/json")
public class CardResource {

	@Autowired
	private CardService cardService;

	@PostMapping(value = "/newcards")
	public ResponseEntity<Void> save(@RequestBody Card obj) {
		obj = cardService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public ResponseEntity<List<CardDTO>> findAll() {
		List<CardDTO> list = cardService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CardDTO> findById(@PathVariable long id) {
		CardDTO obj = cardService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		cardService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/updatecards/{id}")
	public ResponseEntity<Void> update(@PathVariable long id, @RequestBody Card obj) {
		obj = cardService.update(id, obj);
		return ResponseEntity.ok().build();
	}
}
