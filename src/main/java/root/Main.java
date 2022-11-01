package root;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import root.model.Solution;
import root.model.TaskData;
import root.task.BasicMethods;
import root.task.OwnMethods;
import root.task.Solver;
import root.task.SolverDefault;
import root.task.decorator.ToolSetDecoratorCache;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Solver solver = new SolverDefault();
        BasicMethods basicToolset = new BasicMethods();
        basicToolset.withLower(basicToolset).withUpper(basicToolset);
        OwnMethods ownToolset = new OwnMethods();
        ownToolset.withLower(ownToolset).withUpper(ownToolset);

        String resourcePath = Main.class.getClassLoader().getResource("").getPath();
        List<File> files = Stream.of(new File(resourcePath).listFiles())
                .filter(File::isFile)
                .toList();

        for (File file : files) {
            Scanner sc = new Scanner(new File(file.getAbsolutePath()));
            int n = sc.nextInt();
            List<Integer> td = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                td.add(sc.nextInt());
            }
            List<List<Integer>> t = new ArrayList<>(n + 1);
            for (int i = 0; i < n + 1; i++) {
                t.add(new ArrayList<>(n + 1));
                for (int j = 0; j < n + 1; j++) {
                    t.get(i).add(sc.nextInt());
                }
            }
            TaskData data = new TaskData(n, td, t);
            Solution solution = solver.solve(data,
                    new ToolSetDecoratorCache(basicToolset),
                    new ToolSetDecoratorCache(basicToolset),
                    new ToolSetDecoratorCache(basicToolset));
            System.out.println(file.getName());
            System.out.println(solution);
            System.out.println();
            System.out.flush();
        }
    }
}
