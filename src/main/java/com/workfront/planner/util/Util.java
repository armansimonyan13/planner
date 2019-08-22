package com.workfront.planner.util;

import com.workfront.planner.domain.Initiative;
import com.workfront.planner.domain.TimeUnit;

import java.util.ArrayList;
import java.util.List;

public class Util {

	public static List<TimeUnit> getInitiativeTimeUnitList(List<TimeUnit> timeUnitList, Initiative initiative) {
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

	public static int getCollisionCount(List<TimeUnit> initiativeTimeUnitList, List<TimeUnit> otherInitiativeTimeUnitList) {
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
