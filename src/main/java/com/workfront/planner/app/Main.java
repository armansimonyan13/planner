package com.workfront.planner.app;

import com.workfront.planner.display.Display;
import com.workfront.planner.domain.Scenario;
import com.workfront.planner.generator.ProblemGenerator;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

public class Main {

	public static void main(String[] args) {
		SolverFactory<Scenario> solverFactory = SolverFactory.createFromXmlResource("com/workfront/planner/plannerConfig.xml");

		Solver<Scenario> solver = solverFactory.buildSolver();

		Scenario scenario = ProblemGenerator.generate();

		Scenario result = solver.solve(scenario);

		Display.print(result);
	}

}
