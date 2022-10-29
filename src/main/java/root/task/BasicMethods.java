package root.task;

import java.util.ArrayList;
import java.util.List;
import root.collection.Array;
import root.model.TaskData;
import root.utils.CollectionUtils;

public class BasicMethods implements BranchMethod, LowerEstimateMethod, UpperEstimateMethod {
    private LowerEstimateMethod lower;
    private UpperEstimateMethod upper;

    private boolean lessOrEqualThanEach(Array<Integer> v, List<Array<Integer>> V) {
        for (Array<Integer> vv : V) {
            if (v.getSize() > vv.getSize()) return false;
        }
        return true;
    }

    private boolean lowerLessOrEqualThanEach(Array<Integer> v, List<Array<Integer>> V, TaskData data) {
        for (Array<Integer> vv : V) {
            if (lower.lowerEstimate(v, data) > lower.lowerEstimate(vv, data)) return false;
        }
        return true;
    }

    private boolean higherLessOrEqualThanEach(Array<Integer> v, List<Array<Integer>> V, TaskData data) {
        for (Array<Integer> vv : V) {
            if (upper.upperEstimate(v, data) > upper.upperEstimate(vv, data)) return false;
        }
        return true;
    }

    private Array<Integer> branchWide(List<Array<Integer>> V, TaskData data) {
        for (Array<Integer> v : V) {
            if (lessOrEqualThanEach(v, V)) return v;
        }
        throw new Error("");
    }

    private Array<Integer> branchOptimistic(List<Array<Integer>> V, TaskData data) {
        for (Array<Integer> v : V) {
            if (lowerLessOrEqualThanEach(v, V ,data)) return v;
        }
        throw new Error("");
    }

    private Array<Integer> branchRealistic(List<Array<Integer>> V, TaskData data) {
        for (Array<Integer> v : V) {
            if (higherLessOrEqualThanEach(v, V, data)) return v;
        }
        throw new Error("");
    }

    private Array<Integer> branchHybrid(List<Array<Integer>> V, TaskData data) {
        for (Array<Integer> v : V) {
            if (higherLessOrEqualThanEach(v, V, data)
                    && lowerLessOrEqualThanEach(v, V, data)
                    && lessOrEqualThanEach(v, V)) return v;
        }
        throw new Error("");
    }

    public BasicMethods withLower(LowerEstimateMethod lower) {
        this.lower = lower;
        return this;
    }

    public BasicMethods withUpper(UpperEstimateMethod upper) {
        this.upper = upper;
        return this;
    }

    @Override
    public Array<Integer> branch(List<Array<Integer>> V, TaskData data) {
        return
                branchRealistic(V ,data);
//                branchHybrid(V ,data);
//                branchOptimistic(V ,data);
//                branchWide(V ,data);
    }

    @Override
    public int lowerEstimate(Array<Integer> v, TaskData data) {
        List<Integer> all = new ArrayList<>(data.getN());
        for (int i = 1; i <= data.getN(); i++) {
            all.add(i);
        }
        Array<Integer> others = CollectionUtils.subtract(new Array<>(all, 1), v);

        int k = v.getSize();
        Array<Integer> z = new Array<>(data.getN(), 1);

        for (int i = 1; i <= k; i++) {
            int zai = data.getT().get(0, v.get(1));
            for (int j = 1; j <= i - 1; j++) {
                zai += data.getT().get(v.get(j), v.get(j + 1));
            }
            z.set(v.get(i), zai);
        }
        for (int i = 1; i <= others.getSize(); i++) {
            int zbi = data.getT().get(0, v.get(1));
            for (int j = 1; j <= k - 1; j++) {
                zbi += data.getT().get(v.get(j), v.get(j + 1));
            }
            zbi += data.getT().get(v.get(k), others.get(i));
            z.set(others.get(i), zbi);
        }

        Array<Integer> w = new Array<>(data.getN(), 1);
        for (int i = 1; i <= data.getN(); i++) {
            if (z.get(i) <= data.getTD().get(i)) w.set(i, 0);
            else w.set(i, 1);
        }
        int estimate = 0;
        for (Integer wi : w) {
            estimate += wi;
        }
        return estimate;
    }

    private int argmin(Array<Integer> w) {
        int argmin = w.getBegin();
        Integer min = w.get(argmin);
        for (int i = w.getBegin(); i < w.getEnd(); i++) {
            Integer v = w.get(i);
            if (null == v) continue;
            if (null == min || v < min) {
                min = v;
                argmin = i;
            }
        }
        return argmin;
    }

    @Override
    public int upperEstimate(Array<Integer> v, TaskData data) {
        int k = v.getSize();
        Array<Integer> x = new Array<>(v);
        int j = 0;

        while (k + j != data.getN()) {
            List<Integer> all = new ArrayList<>();
            for (int i = 1; i <= data.getN(); i++) {
                all.add(i);
            }
            Array<Integer> others = CollectionUtils.subtract(new Array<>(all, 1), x);
            int z = data.getT().get(0, x.get(1));
            for (int i = 2; i <= data.getN() - (k + j); i++) {
                z += data.getT().get(x.get(i - 1), x.get(i));
            }
            Array<Integer> w = new Array<>(data.getN(), 1);
            for (int i = 1; i <= data.getN() - (k + j); i++) {
                int wbi = Integer.MAX_VALUE;
                if (z + data.getT().get(x.get(k + j), others.get(i)) <= data.getTD().get(others.get(i))) {
                    wbi = data.getTD().get(others.get(i)) - (z + data.getT().get(x.get(k + j), others.get(i)));
                }
                w.set(others.get(i), wbi);
            }
            int bh = argmin(w);
            j += 1;
            x = CollectionUtils.append(x, bh);
        }

        int estimate = 0;
        for (Integer xi : x) {
            estimate += xi;
        }
        return estimate;
    }
}
