package com.sunflower.zappts.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunflower.zappts.entities.pk.ListCardPk;


@Entity
@Table(name = "tb_list_card_player")
public class ListCard implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ListCardPk id = new ListCardPk();

	private Integer numberList;

	public ListCard() {
	}

	public ListCard(Player player, Card card, int numberList) {
		this.numberList = numberList;
		id.setCard(card);
		id.setPlayer(player);
	}

	public ListCard(Player player, Card card, ListCardPk listCardPk, int numberList) {
		this.id = listCardPk;
		this.numberList = numberList;
		id.setCard(card);
		id.setPlayer(player);
	}

	public Card getCard() {
		return id.getCard();
	}

	public void setCard(Card card) {
		id.setCard(card);
	}

	@JsonIgnore
	public Player getPlayer() {
		return id.getPlayer();
	}

	public void setPlayer(Player player) {
		id.setPlayer(player);
	}

	public ListCardPk getId() {
		return id;
	}

	public void setId(ListCardPk id) {
		this.id = id;
	}

	public Integer getNumberList() {
		return numberList;
	}

	public void setNumberList(Integer numberList) {
		this.numberList = numberList;
	}
}