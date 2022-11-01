package root.task.decorator;

import java.util.HashMap;
import java.util.Map;
import root.collection.Array;
import root.model.TaskData;
import root.task.UpperEstimateMethod;

public class UpperEstimateDecoratorCache implements UpperEstimateMethod {
    private UpperEstimateMethod upper;
    private final Map<Array<Integer>, Integer> cache = new HashMap<>();

    public UpperEstimateDecoratorCache(UpperEstimateMethod upper) {
        this.upper = upper;
    }

    @Override
    public int upperEstimate(Array<Integer> leaf, TaskData data) {
        if (cache.containsKey(leaf)) return cache.get(leaf);
        return upper.upperEstimate(leaf, data);
    }
}
