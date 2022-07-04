package com.sunflower.zappts.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunflower.zappts.entities.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{

	Optional<Player> findByName(String username);

}
