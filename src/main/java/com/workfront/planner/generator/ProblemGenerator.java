package com.workfront.planner.generator;

import com.workfront.planner.domain.Initiative;
import com.workfront.planner.domain.Scenario;

import java.util.List;

public class ProblemGenerator {

	public static Scenario generate() {
		ProblemGenerator generator = new ProblemGenerator();
		Scenario scenario = new Scenario();
		scenario.setInitiativeList(List.of(
			generator.createInitiative("i_0", 4),
			generator.createInitiative("i_1", 3),
			generator.createInitiative("i_2", 2),
			generator.createInitiative("i_3", 1)
		));
		return scenario;
	}

	private Initiative createInitiative(String name, int duration) {
		Initiative initiative = new Initiative();
		initiative.setName(name);
		initiative.setDuration(duration);
		return initiative;
	}

}
