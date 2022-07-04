package com.sunflower.zappts.enums;

public enum StatusCard {

	LAMINATED(1), NOTLAMINATED(2);

	private int code;

	StatusCard(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static StatusCard valueOf(int code) {
		for (StatusCard value : StatusCard.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid StatusCard code.");
	}
}
