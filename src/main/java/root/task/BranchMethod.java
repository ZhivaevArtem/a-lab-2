package root.task;

import java.util.List;
import root.collection.Array;
import root.model.TaskData;

public interface BranchMethod {
    Array<Integer> branch(List<Array<Integer>> V, TaskData data);
}
