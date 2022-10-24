package root.task;

import root.model.TaskResult;

public interface Solver {
    TaskResult solveBasic(int ordersCount, int[] deliveryPeriod, int[][] deliveryMatrix);

    TaskResult solveOwn(int ordersCount, int[] deliveryPeriod, int[][] deliveryMatrix);
}
