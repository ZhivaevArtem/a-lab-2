package root.task;

import root.collection.Array;
import root.model.TaskData;

public interface UpperEstimateMethod {
    int upperEstimate(Array<Integer> leaf, TaskData data);
}
