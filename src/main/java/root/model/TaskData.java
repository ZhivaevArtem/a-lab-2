package root.model;

import java.util.List;
import root.collection.Array;
import root.collection.TriangularMatrix;

public class TaskData {
    private int n;
    private Array<Integer> tD;
    private TriangularMatrix<Integer> t;

    public TaskData(int n, List<Integer> tD, List<List<Integer>> t) {
        this.n = n;
        this.tD = new Array<>(n, 1);
        for (int i = 1; i <= n; i++) {
            this.tD.set(i, tD.get(i - 1));
        }
        this.t = new TriangularMatrix<>(n + 1, 0);
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                this.t.set(i, j, t.get(i).get(j));
            }
        }
    }

    public int getN() {
        return n;
    }

    public Array<Integer> getTD() {
        return tD;
    }

    public TriangularMatrix<Integer> getT() {
        return t;
    }
}
