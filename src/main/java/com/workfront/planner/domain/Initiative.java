package com.workfront.planner.domain;

import com.workfront.planner.solver.InitiativeDifficultyComparator;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity(difficultyComparatorClass = InitiativeDifficultyComparator.class)
public class Initiative {

	private String name;

	private TimeUnit startTimeUnit;

	private int duration;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@PlanningVariable(valueRangeProviderRefs = {"timeUnitRange"})
	public TimeUnit getStartTimeUnit() {
		return startTimeUnit;
	}

	@SuppressWarnings("unused")
	public void setStartTimeUnit(TimeUnit startTimeUnit) {
		this.startTimeUnit = startTimeUnit;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "{ \"name\": \"" + name + "\", \"startTimeUnit\": " + startTimeUnit + ", \"duration\": " + duration + " }";
	}

}
