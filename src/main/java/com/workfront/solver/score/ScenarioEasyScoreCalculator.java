package com.workfront.solver.score;

import com.workfront.planner.domain.Initiative;
import com.workfront.planner.domain.Scenario;
import com.workfront.planner.domain.StartDate;
import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

import java.util.ArrayList;
import java.util.List;

public class ScenarioEasyScoreCalculator implements EasyScoreCalculator<Scenario> {

	@Override
	public Score calculateScore(Scenario scenario) {
		List<Initiative> initiativeList = scenario.getInitiativeList();
		List<StartDate> startDateList = scenario.getDateList();

		int score = 0;

		for (int i = 0; i < initiativeList.size(); i++) {
			Initiative initiative = initiativeList.get(i);
			for (int j = 0; j < initiativeList.size(); j++) {
				Initiative otherInitiative = initiativeList.get(j);
				if (initiative == otherInitiative) {
					continue;
				}

				if (initiative.getStartDate() != null && otherInitiative.getStartDate() != null) {
					int startIndex = initiative.getStartDate().getIndex();
					int endIndex = startIndex + initiative.getDuration();
					List<StartDate> initiativeStartDateList = new ArrayList<>();
					for (int z = startIndex; z < endIndex; z++) {
						initiativeStartDateList.add(startDateList.get(z));
					}
					System.out.print("initiativeStartDateList: ");
					System.out.println(initiativeStartDateList);

					startIndex = otherInitiative.getStartDate().getIndex();
					endIndex = startIndex + initiative.getDuration();
					if (endIndex >= startDateList.size()) {
						continue;
					}
					List<StartDate> otherInitiativeStartDateList = new ArrayList<>();
					for (int z = startIndex; z < endIndex; z++) {
						otherInitiativeStartDateList.add(startDateList.get(z));
					}
					System.out.print("otherInitiativeStartDateList: ");
					System.out.println(otherInitiativeStartDateList);

					for (int x = 0; x < initiativeStartDateList.size(); x++) {
						for (int y = 0; y < otherInitiativeStartDateList.size(); y++) {
							if (initiativeStartDateList.get(x).equals(otherInitiativeStartDateList.get(y))) {
								score--;
							}
						}
					}
				}
			}
		}

		System.out.println("Score: " + score);
		return SimpleScore.of(score);
	}

}
