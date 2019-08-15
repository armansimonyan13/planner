package com.workfront.planner.domain;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@PlanningSolution
public class Scenario {

	private List<Initiative> initiativeList;

	private SimpleScore score;

	private static List<StartDate> dateList = List.of(
		Optional.of(new StartDate()).map(sd -> {
			sd.setDate(
				LocalDate.of(2019, 1, 1)
			);
			sd.setIndex(0);
			return sd;
		}).get(),
		Optional.of(new StartDate()).map(sd -> {
			sd.setDate(
				LocalDate.of(2019, 2, 1)
			);
			sd.setIndex(1);
			return sd;
		}).get(),
		Optional.of(new StartDate()).map(sd -> {
			sd.setDate(
				LocalDate.of(2019, 3, 1)
			);
			sd.setIndex(2);
			return sd;
		}).get(),
		Optional.of(new StartDate()).map(sd -> {
			sd.setDate(
				LocalDate.of(2019, 4, 1)
			);
			sd.setIndex(3);
			return sd;
		}).get(),
		Optional.of(new StartDate()).map(sd -> {
			sd.setDate(
				LocalDate.of(2019, 5, 1)
			);
			sd.setIndex(4);
			return sd;
		}).get(),
		Optional.of(new StartDate()).map(sd -> {
			sd.setDate(
				LocalDate.of(2019, 6, 1)
			);
			sd.setIndex(5);
			return sd;
		}).get(),
		Optional.of(new StartDate()).map(sd -> {
			sd.setDate(
				LocalDate.of(2019, 7, 1)
			);
			sd.setIndex(6);
			return sd;
		}).get(),
		Optional.of(new StartDate()).map(sd -> {
			sd.setDate(
				LocalDate.of(2019, 8, 1)
			);
			sd.setIndex(7);
			return sd;
		}).get(),
		Optional.of(new StartDate()).map(sd -> {
			sd.setDate(
				LocalDate.of(2019, 9, 1)
			);
			sd.setIndex(8);
			return sd;
		}).get(),
		Optional.of(new StartDate()).map(sd -> {
			sd.setDate(
				LocalDate.of(2019, 10, 1)
			);
			sd.setIndex(9);
			return sd;
		}).get(),
		Optional.of(new StartDate()).map(sd -> {
			sd.setDate(
				LocalDate.of(2019, 11, 1)
			);
			sd.setIndex(10);
			return sd;
		}).get(),
		Optional.of(new StartDate()).map(sd -> {
			sd.setDate(
				LocalDate.of(2019, 12, 1)
			);
			sd.setIndex(11);
			return sd;
		}).get()
	);

	@PlanningEntityCollectionProperty
	public List<Initiative> getInitiativeList() {
		return initiativeList;
	}

	public void setInitiativeList(List<Initiative> initiativeList) {
		this.initiativeList = initiativeList;
	}

	@ValueRangeProvider(id = "dateRange")
	@ProblemFactCollectionProperty
	public List<StartDate> getDateList() {
		return dateList;
	}

	@PlanningScore
	public SimpleScore getScore() {
		return score;
	}

	public void setScore(SimpleScore score) {
		this.score = score;
	}

}
