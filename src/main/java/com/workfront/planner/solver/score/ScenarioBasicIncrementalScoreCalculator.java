package com.workfront.planner.solver.score;

import com.workfront.planner.domain.Initiative;
import com.workfront.planner.domain.Scenario;
import com.workfront.planner.domain.TimeUnit;
import com.workfront.planner.util.Util;
import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.impl.score.director.incremental.AbstractIncrementalScoreCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ScenarioBasicIncrementalScoreCalculator extends AbstractIncrementalScoreCalculator<Scenario> {

	private Logger logger = LoggerFactory.getLogger(ScenarioBasicIncrementalScoreCalculator.class);

	private Scenario scenario;

	private List<Initiative> insertedInitiativeList;

	private int score;

	@Override
	public void resetWorkingSolution(Scenario scenario) {
		logger.debug("============================================================");
		logger.debug("resetWorkingSolution(" + scenario + ")");

		this.scenario = scenario;
		insertedInitiativeList = new ArrayList<>(scenario.getInitiativeList().size());
		score = 0;
		for (Initiative initiative : scenario.getInitiativeList()) {
			insert(initiative);
		}
	}

	@Override
	public void beforeEntityAdded(Object entity) {
		logger.debug("beforeEntityAdded(" + entity + ")");

		// Do nothing
	}

	@Override
	public void afterEntityAdded(Object entity) {
		logger.debug("afterEntityAdded(" + entity + ")");

		insert((Initiative) entity);
	}

	@Override
	public void beforeVariableChanged(Object entity, String variableName) {
		logger.debug("beforeVariableChanged(" + entity + ", " + variableName + ")");

		retract((Initiative) entity);
	}

	@Override
	public void afterVariableChanged(Object entity, String variableName) {
		logger.debug("afterVariableChanged(" + entity + ", " + variableName + ")");

		insert((Initiative) entity);
	}

	@Override
	public void beforeEntityRemoved(Object entity) {
		logger.debug("beforeEntityRemoved(" + entity + ")");

		retract((Initiative) entity);
	}

	@Override
	public void afterEntityRemoved(Object entity) {
		logger.debug("afterEntityRemoved(" + entity + ")");

		// Do nothing
	}

	@Override
	public Score calculateScore() {
		logger.debug("calculateScore(), score: " + score);

		return SimpleScore.of(score);
	}

	private void insert(Initiative initiative) {
		TimeUnit startTimeUnit = initiative.getStartTimeUnit();
		if (startTimeUnit != null) {
			List<TimeUnit> timeUnitList = scenario.getDateList();
			for (Initiative otherInitiative : insertedInitiativeList) {
				List<TimeUnit> initiativeTimeUnitList = Util.getInitiativeTimeUnitList(timeUnitList, initiative);
				List<TimeUnit> otherInitiativeTimeUnitList = Util.getInitiativeTimeUnitList(timeUnitList, otherInitiative);

				if (initiativeTimeUnitList == null || otherInitiativeTimeUnitList == null) {
					continue;
				}

				//					System.out.println(initiativeTimeUnitList);
				//					System.out.println(otherInitiativeTimeUnitList);

				score += Util.getCollisionCount(initiativeTimeUnitList, otherInitiativeTimeUnitList);
			}
			insertedInitiativeList.add(initiative);
		}
	}

	private void retract(Initiative initiative) {
		TimeUnit startTimeUnit = initiative.getStartTimeUnit();
		if (startTimeUnit != null) {
			insertedInitiativeList.remove(initiative);
			List<TimeUnit> timeUnitList = scenario.getDateList();
			for (Initiative otherInitiative : insertedInitiativeList) {
				List<TimeUnit> initiativeTimeUnitList = Util.getInitiativeTimeUnitList(timeUnitList, initiative);
				List<TimeUnit> otherInitiativeTimeUnitList = Util.getInitiativeTimeUnitList(timeUnitList, otherInitiative);

				if (initiativeTimeUnitList == null || otherInitiativeTimeUnitList == null) {
					continue;
				}

				//					System.out.println(initiativeTimeUnitList);
				//					System.out.println(otherInitiativeTimeUnitList);

				score -= Util.getCollisionCount(initiativeTimeUnitList, otherInitiativeTimeUnitList);
			}
		}
	}

}
