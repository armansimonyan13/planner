package com.workfront.planner.domain;

import com.workfront.planner.generator.TimeUnitGenerator;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;

import java.util.List;

@PlanningSolution
public class Scenario {

	private List<Initiative> initiativeList;

	private SimpleScore score;

	private static List<TimeUnit> timeUnitList = TimeUnitGenerator.generate();

	@PlanningEntityCollectionProperty
	public List<Initiative> getInitiativeList() {
		return initiativeList;
	}

	public void setInitiativeList(List<Initiative> initiativeList) {
		this.initiativeList = initiativeList;
	}

	@ValueRangeProvider(id = "timeUnitRange")
	@ProblemFactCollectionProperty
	public List<TimeUnit> getDateList() {
		return timeUnitList;
	}

	@PlanningScore
	public SimpleScore getScore() {
		return score;
	}

	@SuppressWarnings("unused")
	public void setScore(SimpleScore score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "{ \"score\": " + score + ", \"initiativeList\": " + initiativeList + " }";
	}

}
