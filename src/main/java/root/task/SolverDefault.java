package root.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import root.collection.Array;
import root.model.Solution;
import root.model.TaskData;
import root.utils.CollectionUtils;

public class SolverDefault implements Solver {
    private int y(int i, Array<Integer> x) {
        for (int j = x.getBegin(); j < x.getEnd(); j++) {
            if (x.get(j) == i) return j;
        }
        throw new Error();
    }

    private int z(int i, Array<Integer> x, TaskData data) {
        int z = data.getT().get(0, x.get(1));
        int yy = y(i, x) - 1;
        for (int j = 1; j <= yy; j++) {
            z += data.getT().get(x.get(j), x.get(j + 1));
        }
        return z;
    }

    private int w(int i, Array<Integer> x, TaskData data) {
        if (z(i, x, data) <= data.getTD().get(i)) return 0;
        return 1;
    }

    private boolean restrict(List<Array<Integer>> V, TaskData data, UpperEstimateMethod upper, LowerEstimateMethod lower) {
        for (Array<Integer> v : V) {
            for (Array<Integer> vv : V) {
                if (vv.equals(v)) continue;
                if (upper.upperEstimate(v, data) <= lower.lowerEstimate(vv, data)) {
                    V.remove(vv);
                    return true;
                }
                if (upper.upperEstimate(vv, data) <= lower.lowerEstimate(v, data)) {
                    V.remove(v);
                    return true;
                }
            }
        }
        return false;
    }

    private Array<Integer> branchAndBound(TaskData data, BranchMethod branch, UpperEstimateMethod upper, LowerEstimateMethod lower) {
        List<Array<Integer>> V = new LinkedList<>();
        while (!(
                V.size() == 1 && upper.upperEstimate(V.get(0), data) == lower.lowerEstimate(V.get(0), data)
        )) {
            Array<Integer> v = V.size() == 0
                    ? new Array<>(0, 1)
                    : branch.branch(V, data);

            List<Integer> all = new ArrayList<>(data.getN());
            for (int i = 1; i <= data.getN(); i++) {
                all.add(i);
            }
            Array<Integer> others = CollectionUtils.subtract(new Array<>(all, 1), v);

            V.remove(v);
            for (Integer b : others) {
                V.add(CollectionUtils.append(v, b));
            }

            while (restrict(V, data, upper, lower));
        }
        return V.get(0);
    }

    @Override
    public Solution solve(TaskData data, BranchMethod branch, UpperEstimateMethod upper, LowerEstimateMethod lower) {
        Array<Integer> x = branchAndBound(data, branch, upper, lower);
        int criteria = 0;
        for (int i = 1; i <= data.getN(); i++) {
            criteria += w(i, x, data);
        }
        return new Solution(x.toList(), criteria);
    }
}
