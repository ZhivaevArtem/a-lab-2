package root.task;

import root.collection.Array;
import root.model.TaskData;

public interface LowerEstimateMethod {
    int lowerEstimate(Array<Integer> leaf, TaskData data);
}
