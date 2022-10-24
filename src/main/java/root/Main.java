package root;

import root.task.Task;
import root.utils.Utils;

public class Main {
    public static void main(String[] args) {
        String[] taskFiles = {
                "task_2_01_n3.txt",
                "task_2_02_n3.txt",
                "task_2_03_n10.txt",
                "task_2_04_n10.txt",
                "task_2_05_n10.txt",
                "task_2_06_n15.txt",
                "task_2_07_n15.txt",
                "task_2_08_n50.txt",
                "task_2_09_n50.txt",
                "task_2_10_n50.txt",
        };
        System.out.println(-1 % 5);
//        for (String taskFile : taskFiles) {
//            Task basicTask = Utils.readBasicTaskFromFile(taskFile);
//            Task ownTask = Utils.readOwnTaskFromFile(taskFile);
//            System.out.println(taskFile);
//            System.out.println("Basic:");
//            System.out.println(basicTask.solve());
//            System.out.println("Own:");
//            System.out.println(ownTask.solve());
//            System.out.println();
//        }
    }
}
