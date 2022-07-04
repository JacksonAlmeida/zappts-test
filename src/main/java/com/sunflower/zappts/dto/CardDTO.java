package com.sunflower.zappts.dto;

import java.io.Serializable;

import com.sunflower.zappts.entities.Card;
import com.sunflower.zappts.enums.Language;
import com.sunflower.zappts.enums.StatusCard;

public class CardDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private String name;
	private String edition;
	private Language language;
	private StatusCard statusCard;
	private Double price;

	public CardDTO() {

	}

	public CardDTO(Card x) {
		id = x.getId();
		name = x.getName();
		setLanguage(x.getLanguage());
		setStatusCard(x.getStatusCard());
		edition = x.getEdition();
		price = x.getPrice();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public StatusCard getStatusCard() {
		return statusCard;
	}

	public void setStatusCard(StatusCard statusCard) {
		this.statusCard = statusCard;
	}

}
