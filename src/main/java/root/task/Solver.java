package root.task;

import root.model.Solution;
import root.model.TaskData;

public interface Solver {
    Solution solve(TaskData data, BranchMethod branch, UpperEstimateMethod upper, LowerEstimateMethod lower);
}
