package root.task;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import root.collection.Array;
import root.model.TaskData;
import root.utils.TaskUtils;

public class OwnMethods extends BasicMethods implements ToolSet {
//    @Override
//    public Array<Integer> branch(List<Array<Integer>> V, TaskData data) {
//        int minCount = Integer.MAX_VALUE;
//        int[] w = new int[V.size()];
//        for (int i = 0; i < V.size(); i++) {
//            w[i] = 0;
//            if (V.get(i).getSize() < minCount) {
//                minCount = V.get(i).getSize();
//            }
//            for (int j = V.get(i).getBegin(); j < V.get(i).getEnd(); j++) {
//                w[i] += TaskUtils.w(j, V.get(i), data);
//            }
//        }
//        int minW = Integer.MAX_VALUE, mini = -1;
//        for (int i = 0; i < V.size(); i++) {
//            if (V.get(i).getSize() == minCount && w[i] < minW) {
//                minW = w[i];
//                mini = i;
//            }
//        }
//        return V.get(mini);
//    }

    @Override
    public Array<Integer> branch(List<Array<Integer>> V, TaskData data) {
//        int maxSize = V.stream().mapToInt(Array::getSize).max().getAsInt();
        final Map<Integer, Set<Array<Integer>>> byEstimate = new HashMap<>();
        V.stream()
//                .filter(v -> v.getSize() == maxSize)
                .forEach((v) -> {
                    int k = this.lower.lowerEstimate(v, data);
                    if (!byEstimate.containsKey(k)) {
                        byEstimate.put(k, new HashSet<>());
                    }
                    byEstimate.get(k).add(v);
                });
        int key = byEstimate.keySet().stream().mapToInt(k -> k).min().getAsInt();
        Set<Array<Integer>> byEst = byEstimate.get(key);

        final Map<Integer, Set<Array<Integer>>> map2 = new HashMap<>();
        byEst.forEach(e -> {
            int k = this.upper.upperEstimate(e, data);
            if (!map2.containsKey(k)) {
                map2.put(k, new HashSet<>());
            }
            map2.get(k).add(e);
        });


        key = map2.keySet().stream().mapToInt(k -> k).min().getAsInt();
        Set<Array<Integer>> setR = map2.get(key);
        final int maxSize1 = setR.stream().mapToInt(Array::getSize).max().getAsInt();
        Optional<Array<Integer>> any = setR.stream().filter(v -> v.getSize() == maxSize1).findAny();
        return any.get();
    }
}
