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

import com.sunflower.zappts.dto.PlayerDTO;
import com.sunflower.zappts.entities.Player;
import com.sunflower.zappts.services.PlayerService;

@RestController
@RequestMapping(value = "/api/players", produces = "application/json")
public class PlayerResource {

	@Autowired
	private PlayerService playerService;

	@PostMapping(value = "/newplayer")
	public ResponseEntity<Void> save(@RequestBody Player obj) {
		obj = playerService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public ResponseEntity<List<PlayerDTO>> findAll() {
		List<PlayerDTO> list = playerService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Player> findById(@PathVariable long id) {
		Player obj = playerService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		playerService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/updaterole/{id}")
	public ResponseEntity<Void> update(@PathVariable long id, @RequestBody Player obj) {
		obj = playerService.update(id, obj);
		return ResponseEntity.ok().build();
	}
}
