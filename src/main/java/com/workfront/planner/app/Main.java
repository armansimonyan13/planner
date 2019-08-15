package com.workfront.planner.app;

import com.workfront.planner.domain.Initiative;
import com.workfront.planner.domain.Scenario;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import java.util.List;
import java.util.Optional;

public class Main {

	public static void main(String[] args) {
		SolverFactory<Scenario> solverFactory = SolverFactory.createFromXmlResource("com/workfront/planner/plannerConfig.xml");

		Solver<Scenario> solver = solverFactory.buildSolver();

		Scenario scenario = createScenario();

		Scenario result = solver.solve(scenario);

		display(result);
	}

	private static Scenario createScenario() {
		Scenario scenario = new Scenario();
		scenario.setInitiativeList(List.of(
			Optional.of(new Initiative()).map(i -> {
				i.setName("i_0");
				i.setDuration(4);
				return i;
			}).get(),
			Optional.of(new Initiative()).map(i -> {
				i.setName("i_1");
				i.setDuration(3);
				return i;
			}).get(),
			Optional.of(new Initiative()).map(i -> {
				i.setName("i_2");
				i.setDuration(2);
				return i;
			}).get(),
			Optional.of(new Initiative()).map(i -> {
				i.setName("i_3");
				i.setDuration(1);
				return i;
			}).get()
		));
		return scenario;
	}

	private static void display(Scenario scenario) {
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
