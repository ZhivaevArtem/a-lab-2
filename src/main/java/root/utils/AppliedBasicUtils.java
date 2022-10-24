package root.utils;

import java.util.ArrayList;
import java.util.List;
import root.model.CyclicArray;
import root.model.CyclicSquareMatrix;

public class AppliedBasicUtils {

    public static int upperEstimateBasic(CyclicArray<Integer> a, Integer n, CyclicSquareMatrix<Integer> t, CyclicArray<Integer> tD) {
        final int k = a.size();
        assert tD.size() == n;
        assert t.size() == n + 1;
        CyclicArray<Integer> b = new CyclicArray<>(n - k);
        int otherNodesTop = 0;
        for (int i = 1; i <= n; i++) {
            if (!includes(a, i)) {
                b.set(otherNodesTop++, i);
            }
        }
        assert k + b.size() == n;
        CyclicArray<Integer> x = new CyclicArray<>(k);
        for (int i = 1; i <= k; i++) {
            x.set(i, a.get(i));
        }
        if (k == n) {
            int estimate = 0;
            for (Integer ai : a) {
                estimate += tD.get(ai);
            }
            return estimate;
        }
        CyclicArray<Integer> w = new CyclicArray<>(n);
        int j = 0;
        do {
            b = new CyclicArray<>(n - (k + j));
            otherNodesTop = 0;
            for (int i = 1; i <= n; i++) {
                if (!includes(x, i)) {
                    b.set(otherNodesTop++, i);
                }
            }
            int z = t.get(0, x.get(1));
            for (int i = 2; i <= n - (k + j); i++) {
                z += x.get(i - 1) * x.get(i);
            }
            for (int i = 1; i <= n - (k + j); i++) {
                int wbi = Integer.MAX_VALUE;
                if (z + t.get(x.get(k + j), b.get(i)) <= tD.get(b.get(i))) {
                    wbi = tD.get(b.get(i)) - (z + t.get(x.get(k + j), b.get(i)));
                }
                w.set(b.get(i), wbi);
            }
            int h = argmin(w);
            j += 1;
            CyclicArray<Integer> newX = new CyclicArray<>(k + j);
            for (int i = 1; i <= k + j - 1; i++) {
                newX.set(i, x.get(i));
            }
            newX.set(k + j, b.get(h));
            x = newX;
        } while (k + j != n);
        int estimate = 0;
        for (Integer wi : w) {
            estimate += wi;
        }
        return estimate;
    }

    public static int lowerEstimateBasic(CyclicArray<Integer> a, Integer n, CyclicSquareMatrix<Integer> t, CyclicArray<Integer> tD) {
        final int k = a.size();
        assert tD.size() == n;
        assert t.size() == n + 1;
        CyclicArray<Integer> b = new CyclicArray<>(n - k);
        int otherNodesTop = 0;
        for (int i = 1; i <= n; i++) {
            if (!includes(a, i)) {
                b.set(otherNodesTop++, i);
            }
        }
        assert k + b.size() == n;

        CyclicArray<Integer> z = new CyclicArray<>(n);
        for (int i = 1; i <= k; i++) {
            int zai = t.get(0, a.get(1));
            for (int j = 1; j <= i - 1; j++) {
                zai += t.get(a.get(j), a.get(j + 1));
            }
            z.set(a.get(i), zai);
        }
        for (int i = 1; i <= i - k; i++) {
            int zbi = t.get(0, a.get(i));
            for (int j = 1; j <= k - 1; j++) {
                zbi += t.get(a.get(j), a.get(j + 1));
            }
            zbi += t.get(a.get(k), b.get(i));
            z.set(b.get(i), zbi);
        }
        CyclicArray<Integer> w = new CyclicArray<>(n);
        for (int i = 1; i <= n; i++) {
            int wi = 1;
            if (z.get(i) <= tD.get(i)) {
                wi = 0;
            }
            w.set(i, wi);
        }
        int estimate = 0;
        for (Integer wi : w) {
            estimate += wi;
        }
        return estimate;
    }

    public static CyclicArray<Integer> branch(CyclicArray<Integer> v) {

    }

    public static void branchAndBound(int n, CyclicArray<Integer> tD, CyclicSquareMatrix<Integer> t) {
        List<CyclicArray<Integer>> v = new ArrayList<>();
        CyclicArray<Integer> v = new CyclicArray<>(0);
        do {
            v = branch(v);
        } while (!(v.size() == 1 && upperEstimateBasic(v, n, t, tD) == lowerEstimateBasic(v, n, t, tD)));

    }
}
