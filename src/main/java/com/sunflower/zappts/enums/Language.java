package com.sunflower.zappts.enums;

public enum Language {

	POETUGUESE(1),
	ENGLISH(2),
	jAPONESE(3);
	
	private int code;
	
	Language(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static Language valueOf(int code) {
		for(Language value : Language.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid language code.");
	}
}
