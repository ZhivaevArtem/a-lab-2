package root.utils;

import root.collection.Array;
import root.model.TaskData;

public class TaskUtils {
    public static int y(int i, Array<Integer> x) {
        for (int j = x.getBegin(); j < x.getEnd(); j++) {
            if (x.get(j) == i) return j;
        }
        return x.getBegin();
    }

    public static int z(int i, Array<Integer> x, TaskData data) {
        int z = data.getT().get(0, x.get(x.getBegin()));
        int yy = y(i, x) - 1;
        for (int j = x.getBegin(); j <= yy; j++) {
            z += data.getT().get(x.get(j), x.get(j + 1));
        }
        return z;
    }

    public static int w(int i, Array<Integer> x, TaskData data) {
        if (z(i, x, data) <= data.getTD().get(i)) return 0;
        return 1;
    }
}
