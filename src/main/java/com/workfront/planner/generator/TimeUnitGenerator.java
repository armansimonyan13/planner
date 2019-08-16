package com.workfront.planner.generator;

import com.workfront.planner.domain.TimeUnit;

import java.time.LocalDate;
import java.util.List;

public class TimeUnitGenerator {

	public static List<TimeUnit> generate() {
		TimeUnitGenerator generator = new TimeUnitGenerator();

		return List.of(
			generator.createStartDate(2019, 1, 1, 0),
			generator.createStartDate(2019, 2, 1, 1),
			generator.createStartDate(2019, 3, 1, 2),
			generator.createStartDate(2019, 4, 1, 3),
			generator.createStartDate(2019, 5, 1, 4),
			generator.createStartDate(2019, 6, 1, 5),
			generator.createStartDate(2019, 7, 1, 6),
			generator.createStartDate(2019, 8, 1, 7),
			generator.createStartDate(2019, 9, 1, 8),
			generator.createStartDate(2019, 10, 1, 9),
			generator.createStartDate(2019, 11, 1, 10),
			generator.createStartDate(2019, 12, 1, 11)
		);
	}

	private TimeUnit createStartDate(int year, int month, int dayOfMonth, int index) {
		TimeUnit timeUnit = new TimeUnit();
		timeUnit.setDate(
			LocalDate.of(year, month, dayOfMonth)
		);
		timeUnit.setIndex(index);
		return timeUnit;
	}

}
