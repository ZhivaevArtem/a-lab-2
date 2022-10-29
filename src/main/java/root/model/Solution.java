package root.model;

import java.util.List;

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
}
