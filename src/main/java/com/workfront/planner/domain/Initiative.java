package com.workfront.planner.domain;

import com.workfront.planner.solver.InitiativeDifficultyComparator;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity(difficultyComparatorClass = InitiativeDifficultyComparator.class)
public class Initiative {

	private String name;

	private StartDate startDate;

	private int duration;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@PlanningVariable(valueRangeProviderRefs = {"dateRange"})
	public StartDate getStartDate() {
		return startDate;
	}

	public void setStartDate(StartDate startDate) {
		this.startDate = startDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "{name: " + name + ", startDate: " + startDate + ", duration: " + duration + "}";
	}

}
