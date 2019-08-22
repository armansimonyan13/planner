package com.workfront.planner.solver.score;

import com.workfront.planner.domain.Initiative;
import com.workfront.planner.domain.Scenario;
import com.workfront.planner.domain.TimeUnit;
import com.workfront.planner.util.Util;
import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

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
					List<TimeUnit> initiativeTimeUnitList = Util.getInitiativeTimeUnitList(timeUnitList, initiative);
					List<TimeUnit> otherInitiativeTimeUnitList = Util.getInitiativeTimeUnitList(timeUnitList, otherInitiative);

					if (initiativeTimeUnitList == null || otherInitiativeTimeUnitList == null) {
						continue;
					}

//					System.out.println(initiativeTimeUnitList);
//					System.out.println(otherInitiativeTimeUnitList);

					score += Util.getCollisionCount(initiativeTimeUnitList, otherInitiativeTimeUnitList);
				}
			}
		}

//		System.out.println(score);

		return SimpleScore.of(score);
	}

}
