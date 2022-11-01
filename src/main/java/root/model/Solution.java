package root.model;

import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    private int leafsVisited;
    private List<Integer> order;
    private int criteria;

    public Solution(List<Integer> order, int criteria, int leafsVisited) {
        this.order = order;
        this.criteria = criteria;
        this.leafsVisited = leafsVisited;
    }

    public List<Integer> getOrder() {
        return order;
    }

    public int getCriteria() {
        return criteria;
    }

    @Override
    public String toString() {
        return leafsVisited + " [" + order.stream().map(String::valueOf).collect(Collectors.joining(", ")) + "] " + criteria;
    }
}
