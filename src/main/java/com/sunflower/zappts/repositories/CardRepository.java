package com.sunflower.zappts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunflower.zappts.entities.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
}
