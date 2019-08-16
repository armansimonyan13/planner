package com.workfront.solver.score;

import com.workfront.planner.domain.Initiative;
import com.workfront.planner.domain.Scenario;
import com.workfront.planner.domain.TimeUnit;
import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

import java.util.ArrayList;
import java.util.List;

public class ScenarioEasyScoreCalculator implements EasyScoreCalculator<Scenario> {

	@Override
	public Score calculateScore(Scenario scenario) {
		List<Initiative> initiativeList = scenario.getInitiativeList();
		List<TimeUnit> timeUnitList = scenario.getDateList();

		int score = 0;

		for (int i = 0; i < initiativeList.size(); i++) {
			Initiative initiative = initiativeList.get(i);
			for (int j = 0; j < initiativeList.size(); j++) {
				Initiative otherInitiative = initiativeList.get(j);
				if (initiative == otherInitiative) {
					continue;
				}

				if (initiative.getStartTimeUnit() != null && otherInitiative.getStartTimeUnit() != null) {
					int startIndex = initiative.getStartTimeUnit().getIndex();
					int endIndex = startIndex + initiative.getDuration();
					List<TimeUnit> initiativeTimeUnitList = new ArrayList<>();
					for (int z = startIndex; z < endIndex; z++) {
						initiativeTimeUnitList.add(timeUnitList.get(z));
					}
					System.out.print("initiativeTimeUnitList: ");
					System.out.println(initiativeTimeUnitList);

					startIndex = otherInitiative.getStartTimeUnit().getIndex();
					endIndex = startIndex + initiative.getDuration();
					if (endIndex >= timeUnitList.size()) {
						continue;
					}
					List<TimeUnit> otherInitiativeTimeUnitList = new ArrayList<>();
					for (int z = startIndex; z < endIndex; z++) {
						otherInitiativeTimeUnitList.add(timeUnitList.get(z));
					}
					System.out.print("otherInitiativeTimeUnitList: ");
					System.out.println(otherInitiativeTimeUnitList);

					for (int x = 0; x < initiativeTimeUnitList.size(); x++) {
						for (int y = 0; y < otherInitiativeTimeUnitList.size(); y++) {
							if (initiativeTimeUnitList.get(x).equals(otherInitiativeTimeUnitList.get(y))) {
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
