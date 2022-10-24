package root.task.impl;

import root.model.TaskResult;
import root.task.Task;
import root.task.solver.impl.SolverDefault;

public class OwnTask implements Task {
    private int ordersCount;
    private int[] deliveryPeriods;
    private int[][] deliveryMatrix;

    public OwnTask(int ordersCount, int[] deliveryPeriods, int[][] deliveryMatrix) {
        this.ordersCount = ordersCount;
        this.deliveryPeriods = deliveryPeriods;
        this.deliveryMatrix = deliveryMatrix;
    }

    public TaskResult solve() {
        return new SolverDefault().solveOwn(ordersCount, deliveryPeriods, deliveryMatrix);
    }
}
