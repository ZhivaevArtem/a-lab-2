package root.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import root.collection.Array;
import root.model.Solution;
import root.model.TaskData;
import root.utils.CollectionUtils;
import root.utils.TaskUtils;

public class SolverDefault implements Solver {
    private boolean restrict(List<Array<Integer>> V, TaskData data, UpperEstimateMethod upper, LowerEstimateMethod lower, int[] iOpt, int[] jOpt) {
        int i = iOpt[0], j = jOpt[0];
        for (; i < V.size(); i++) {
            if (j <= i) j = i + 1;
            for (; j < V.size(); j++) {
                Array<Integer> v = V.get(i);
                Array<Integer> vv = V.get(j);
                if (upper.upperEstimate(v, data) <= lower.lowerEstimate(vv, data)) {
                    V.remove(vv);
                    iOpt[0] = i;
                    jOpt[0] = j;
                    return true;
                }
                if (upper.upperEstimate(vv, data) <= lower.lowerEstimate(v, data)) {
                    V.remove(v);
                    iOpt[0] = i;
                    jOpt[0] = j;
                    return true;
                }
            }
            j = 0;
        }
        return false;
    }

    private int leafsVisited = 0;
    private int getLeafsVisited() {
        int l = leafsVisited;
        leafsVisited = 0;
        return l;
    }
    private Array<Integer> branchAndBound(TaskData data, BranchMethod branch, UpperEstimateMethod upper, LowerEstimateMethod lower) {
        int leafsVisited = 0;
        List<Array<Integer>> V = new LinkedList<>();
        while (!(
                V.size() == 1 && V.get(0).getSize() == data.getN() && upper.upperEstimate(V.get(0), data) == lower.lowerEstimate(V.get(0), data)
        )) {
            Array<Integer> v = V.size() == 0
                    ? new Array<>(0, 1)
                    : V.size() == 1
                            ? V.get(0)
                            : branch.branch(V, data);

            Array<Integer> others = CollectionUtils.subtract(
                    CollectionUtils.range(1, data.getN() + 1, 1),
                    v
            );

            V.remove(v);
            leafsVisited++;
            for (Integer b : others) {
                V.add(CollectionUtils.append(v, b));
            }

            int[] i = new int[] { 0 }, j = new int[] { 0 };
            while (restrict(V, data, upper, lower, i, j)) {
                leafsVisited++;
            }
        }
        leafsVisited += V.size();
        this.leafsVisited = leafsVisited;
        return V.get(0);
    }

    @Override
    public Solution solve(TaskData data, BranchMethod branch, UpperEstimateMethod upper, LowerEstimateMethod lower) {
        Array<Integer> x = branchAndBound(data, branch, upper, lower);
        int c = 0;
        for (int i = 1; i <= data.getN(); i++) {
            c += TaskUtils.w(i, x, data);
        }
        return new Solution(x.toList(), c, getLeafsVisited());
    }
}
