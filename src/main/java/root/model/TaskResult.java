package root.model;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TaskResult {
    private int[] order;

    public TaskResult(int[] order) {
        this.order = order;
    }

    public int[] getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return Arrays.stream(this.order).mapToObj(String::valueOf).collect(Collectors.joining(", "));
    }
}
