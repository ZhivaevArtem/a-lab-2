package root.task.impl;

import root.model.TaskResult;
import root.task.Task;
import root.task.solver.impl.SolverDefault;

public class BasicTask implements Task {
    private int ordersCount;
    private int[] deliveryPeriods;
    private int[][] deliveryMatrix;

    public BasicTask(int ordersCount, int[] deliveryPeriods, int[][] deliveryMatrix) {
        this.deliveryMatrix = deliveryMatrix;
        this.deliveryPeriods = deliveryPeriods;
        this.ordersCount = ordersCount;
    }

    public TaskResult solve() {
        return new SolverDefault().solveBasic(this.ordersCount, this.deliveryPeriods, this.deliveryMatrix);
    }
}
