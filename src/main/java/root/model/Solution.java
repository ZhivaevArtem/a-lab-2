package root.model;

import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    private List<Integer> order;
    private int criteria;

    public Solution(List<Integer> order, int criteria) {
        this.order = order;
        this.criteria = criteria;
    }

    public List<Integer> getOrder() {
        return order;
    }

    public int getCriteria() {
        return criteria;
    }

    @Override
    public String toString() {
        return "[" + order.stream().map(String::valueOf).collect(Collectors.joining(", ")) + "] " + criteria;
    }
}
