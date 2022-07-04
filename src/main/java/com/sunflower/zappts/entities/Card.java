package com.sunflower.zappts.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunflower.zappts.enums.Language;
import com.sunflower.zappts.enums.StatusCard;

@Entity
@Table(name = "tb_card")
public class Card implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private String edition;
	private Integer language;
	private Integer statusCard;
	private Double price;

	@OneToMany(mappedBy = "id.player", fetch = FetchType.EAGER)
	private List<ListCard> listPlayers = new ArrayList<>();

	public Card() {
	}

	public Card(long id, String name, String edition, Language language, StatusCard statusCard, Double price,
			List<ListCard> listPlayers) {
		this.id = id;
		this.name = name;
		this.edition = edition;
		setLanguage(language);
		setStatusCard(statusCard);
		this.price = price;
		this.listPlayers = listPlayers;
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

	public Language getLanguage() {
		return Language.valueOf(language);
	}

	public void setLanguage(Language language) {
		if (language != null)
			this.language = language.getCode();
	}

	public StatusCard getStatusCard() {
		return StatusCard.valueOf(statusCard);
	}

	public void setStatusCard(StatusCard statusCard) {
		if (statusCard != null)
			this.statusCard = statusCard.getCode();
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<ListCard> getListCard() {
		return listPlayers;
	}

	public void setListCard(List<ListCard> listPlayers) {
		this.listPlayers = listPlayers;
	}

	public Set<Player> getPlayes() {
		Set<Player> set = new HashSet<>();
		for (ListCard x : listPlayers) {
			set.add(x.getPlayer());
		}
		return set;
	}
}
