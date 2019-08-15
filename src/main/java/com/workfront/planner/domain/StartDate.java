package com.workfront.planner.domain;

import java.time.LocalDate;

public class StartDate {

	private LocalDate date;

	private int index;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "{index: " + index + ", date: " + date + "}";
	}

}
