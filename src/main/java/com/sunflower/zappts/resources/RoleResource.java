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

import com.sunflower.zappts.dto.RoleDTO;
import com.sunflower.zappts.entities.Role;
import com.sunflower.zappts.services.RoleService;

@RestController
@RequestMapping(value = "/api/roles", produces = "application/json")
public class RoleResource {

	@Autowired
	private RoleService roleService;

	@PostMapping(value = "/newrole")
	public ResponseEntity<Void> save(@RequestBody Role obj) {
		obj = roleService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public ResponseEntity<List<RoleDTO>> findAll() {
		List<RoleDTO> list = roleService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<RoleDTO> findById(@PathVariable long id) {
		RoleDTO obj = roleService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		roleService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/updaterole/{id}")
	public ResponseEntity<Void> update(@PathVariable long id, @RequestBody Role obj) {
		obj = roleService.update(id, obj);
		return ResponseEntity.ok().build();
	}
}
