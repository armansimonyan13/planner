package com.workfront.planner.display;

import com.workfront.planner.domain.Initiative;
import com.workfront.planner.domain.Scenario;

import java.util.List;

public class Display {

	public static void print(Scenario scenario) {
		List<Initiative> initiativeList = scenario.getInitiativeList();

		for (int i = 0; i < initiativeList.size(); i++) {
			Initiative initiative = initiativeList.get(i);
			print(initiative.getName());
			for (int offset = 0; offset < initiative.getStartDate().getDate().getMonthValue(); offset++) {
				print(" ");
			}
			for (int d = 0; d < initiative.getDuration(); d++) {
				print("=");
			}
			print("\n");
		}
	}

	private static void print(String string) {
		System.out.print(string);
	}

}
