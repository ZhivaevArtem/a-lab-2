package root.task;

import java.util.List;
import root.collection.Array;
import root.model.TaskData;

public class OwnMethods implements BranchMethod, LowerEstimateMethod, UpperEstimateMethod {
    @Override
    public Array<Integer> branch(List<Array<Integer>> V, TaskData data) {
        return null;
    }

    @Override
    public int lowerEstimate(Array<Integer> leaf, TaskData data) {
        return 0;
    }

    @Override
    public int upperEstimate(Array<Integer> leaf, TaskData data) {
        return 0;
    }
}
