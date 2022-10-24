package root.task.solver.impl;

import root.model.TaskResult;
import root.task.Solver;

public class SolverDefault implements Solver {
    public TaskResult solveBasic(int ordersCount, int[] deliveryPeriod, int[][] deliveryMatrix) {
        return this.solveOwn(ordersCount, deliveryPeriod, deliveryMatrix);
    }

    public TaskResult solveOwn(int ordersCount, int[] deliveryPeriod, int[][] deliveryMatrix) {
        return this.solveBasic(ordersCount, deliveryPeriod, deliveryMatrix);
    }
}
