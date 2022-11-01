package root.task.decorator;

import java.util.HashMap;
import java.util.Map;
import root.collection.Array;
import root.model.TaskData;
import root.task.LowerEstimateMethod;

public class LowerEstimateDecoratorCache implements LowerEstimateMethod {
    private LowerEstimateMethod lower;

    public LowerEstimateDecoratorCache(LowerEstimateMethod lower) {
        this.lower = lower;
    }

    private Map<Array<Integer>, Integer> cache = new HashMap<>();
    @Override
    public int lowerEstimate(Array<Integer> leaf, TaskData data) {
        if (cache.containsKey(leaf)) return cache.get(leaf);
        return lower.lowerEstimate(leaf, data);
    }
}
