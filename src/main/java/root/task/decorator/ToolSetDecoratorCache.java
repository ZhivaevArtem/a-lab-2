package root.task.decorator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import root.collection.Array;
import root.model.TaskData;
import root.task.BranchMethod;
import root.task.LowerEstimateMethod;
import root.task.ToolSet;
import root.task.UpperEstimateMethod;

public class ToolSetDecoratorCache implements LowerEstimateMethod, UpperEstimateMethod, BranchMethod {
    private LowerEstimateMethod lower;
    private UpperEstimateMethod upper;
    private BranchMethod branch;

    public ToolSetDecoratorCache(ToolSet toolset) {
        this.lower = toolset;
        this.upper = toolset;
        this.branch = toolset;
    }

    private final Map<Array<Integer>, Integer> lowerCache = new HashMap<>();
    private final Map<Array<Integer>, Integer> upperCache = new HashMap<>();

    @Override
    public Array<Integer> branch(List<Array<Integer>> V, TaskData data) {
        return branch.branch(V, data);
    }

    @Override
    public int lowerEstimate(Array<Integer> leaf, TaskData data) {
        if (lowerCache.containsKey(leaf)) {
//            System.out.println("ToolSetDecoratorCache.lowerEstimate");
            return lowerCache.get(leaf);
        }
        int e = lower.lowerEstimate(leaf, data);
        lowerCache.put(leaf, e);
        return e;
    }

    @Override
    public int upperEstimate(Array<Integer> leaf, TaskData data) {
        if (upperCache.containsKey(leaf)) {
//            System.out.println("ToolSetDecoratorCache.upperEstimate");
            return upperCache.get(leaf);
        }
        int e = upper.upperEstimate(leaf, data);
        upperCache.put(leaf, e);
        return e;
    }
}
