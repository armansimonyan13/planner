package com.workfront.planner.solver.score;

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
		int score = 0;

		List<Initiative> initiativeList = scenario.getInitiativeList();
		List<TimeUnit> timeUnitList = scenario.getDateList();

		for (Initiative initiative : initiativeList) {
			for (Initiative otherInitiative : initiativeList) {
				if (initiative == otherInitiative) {
					continue;
				}

				if (initiative.getStartTimeUnit() != null && otherInitiative.getStartTimeUnit() != null) {
					List<TimeUnit> initiativeTimeUnitList = getInitiativeTimeUnitList(timeUnitList, initiative);
					List<TimeUnit> otherInitiativeTimeUnitList = getInitiativeTimeUnitList(timeUnitList, otherInitiative);

					if (initiativeTimeUnitList == null || otherInitiativeTimeUnitList == null) {
						continue;
					}

//					System.out.println(initiativeTimeUnitList);
//					System.out.println(otherInitiativeTimeUnitList);

					score += getCollisionCount(initiativeTimeUnitList, otherInitiativeTimeUnitList);
				}
			}
		}

//		System.out.println(score);

		return SimpleScore.of(score);
	}

	private int getCollisionCount(List<TimeUnit> initiativeTimeUnitList, List<TimeUnit> otherInitiativeTimeUnitList) {
		int score = 0;
		for (TimeUnit timeUnit : initiativeTimeUnitList) {
			for (TimeUnit otherTimUnit : otherInitiativeTimeUnitList) {
				if (timeUnit.equals(otherTimUnit)) {
					score--;
				}
			}
		}
		return score;
	}

	private List<TimeUnit> getInitiativeTimeUnitList(List<TimeUnit> timeUnitList, Initiative initiative) {
		List<TimeUnit> initiativeTimeUnitList = new ArrayList<>();
		int startIndex = initiative.getStartTimeUnit().getIndex();
		int endIndex = startIndex + initiative.getDuration();
		if (endIndex >= timeUnitList.size()) {
			return null;
		}
		for (int z = startIndex; z < endIndex; z++) {
			initiativeTimeUnitList.add(timeUnitList.get(z));
		}
		return initiativeTimeUnitList;
	}

}
