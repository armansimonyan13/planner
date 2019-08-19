package com.workfront.planner.solver.score;

import com.workfront.planner.domain.Initiative;
import com.workfront.planner.domain.Scenario;
import com.workfront.planner.domain.TimeUnit;
import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.impl.score.director.incremental.AbstractIncrementalScoreCalculator;

import java.util.ArrayList;
import java.util.List;

public class ScenarioBasicIncrementalScoreCalculator extends AbstractIncrementalScoreCalculator<Scenario> {

	private Scenario scenario;

	private List<Initiative> insertedInitiativeList;

	private int score;

	@Override
	public void resetWorkingSolution(Scenario scenario) {
		this.scenario = scenario;
		insertedInitiativeList = new ArrayList<>(scenario.getInitiativeList().size());
		score = 0;
		for (Initiative initiative : scenario.getInitiativeList()) {
			insert(initiative);
		}
	}

	@Override
	public void beforeEntityAdded(Object entity) {
		// Do nothing
	}

	@Override
	public void afterEntityAdded(Object entity) {
		insert((Initiative) entity);
	}

	@Override
	public void beforeVariableChanged(Object entity, String variableName) {
		retract((Initiative) entity);
	}

	@Override
	public void afterVariableChanged(Object entity, String variableName) {
		insert((Initiative) entity);
	}

	@Override
	public void beforeEntityRemoved(Object entity) {
		retract((Initiative) entity);
	}

	@Override
	public void afterEntityRemoved(Object entity) {
		// Do nothing
	}

	@Override
	public Score calculateScore() {
		return SimpleScore.of(score);
	}

	private void insert(Initiative initiative) {
		TimeUnit startTimeUnit = initiative.getStartTimeUnit();
		if (startTimeUnit != null) {
			List<TimeUnit> timeUnitList = scenario.getDateList();
			for (Initiative otherInitiative : insertedInitiativeList) {
				List<TimeUnit> initiativeTimeUnitList = getInitiativeTimeUnitList(timeUnitList, initiative);
				List<TimeUnit> otherInitiativeTimeUnitList = getInitiativeTimeUnitList(timeUnitList, otherInitiative);

				if (initiativeTimeUnitList == null || otherInitiativeTimeUnitList == null) {
					continue;
				}

				//					System.out.println(initiativeTimeUnitList);
				//					System.out.println(otherInitiativeTimeUnitList);

				score += getCollisionCount(initiativeTimeUnitList, otherInitiativeTimeUnitList);
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
				List<TimeUnit> initiativeTimeUnitList = getInitiativeTimeUnitList(timeUnitList, initiative);
				List<TimeUnit> otherInitiativeTimeUnitList = getInitiativeTimeUnitList(timeUnitList, otherInitiative);

				if (initiativeTimeUnitList == null || otherInitiativeTimeUnitList == null) {
					continue;
				}

				//					System.out.println(initiativeTimeUnitList);
				//					System.out.println(otherInitiativeTimeUnitList);

				score -= getCollisionCount(initiativeTimeUnitList, otherInitiativeTimeUnitList);
			}
		}
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

}
