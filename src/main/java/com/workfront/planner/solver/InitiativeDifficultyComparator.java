package com.workfront.planner.solver;

import com.workfront.planner.domain.Initiative;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Comparator;

public class InitiativeDifficultyComparator implements Comparator<Initiative> {

	@Override
	public int compare(Initiative o1, Initiative o2) {
		int duration1 = o1.getDuration();
		int duration2 = o2.getDuration();
		return new CompareToBuilder()
			.append(duration1, duration2)
			.toComparison();
	}

}
