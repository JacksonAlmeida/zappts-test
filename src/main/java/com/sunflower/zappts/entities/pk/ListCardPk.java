package com.sunflower.zappts.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunflower.zappts.entities.Card;
import com.sunflower.zappts.entities.Player;

@Embeddable
public class ListCardPk implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "card_id")
	private Card card;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public int hashCode() {
		return Objects.hash(card, player);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListCardPk other = (ListCardPk) obj;
		return Objects.equals(card, other.card) && Objects.equals(player, other.player);
	}

}
