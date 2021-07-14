package com.mobiquity.model;

import java.util.List;

public class OpeningHours {
	private int dayOfWeek;
	private List<Hours> hours;

	public OpeningHours() {
		super();
	}

	public OpeningHours(int dayOfWeek, List<Hours> hours) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.hours = hours;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public List<Hours> getHours() {
		return hours;
	}

	public void setHours(List<Hours> hours) {
		this.hours = hours;
	}

}
