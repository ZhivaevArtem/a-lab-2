package root.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import root.model.CyclicArray;
import root.model.CyclicSquareMatrix;
import root.task.impl.BasicTask;
import root.task.impl.OwnTask;

public class Utils {
    public static BasicTask readBasicTaskFromFile(String filePath) {
        Scanner scanner = new Scanner(System.in);
        int deliveriesCount = scanner.nextInt();
        int[] deadlines = new int[deliveriesCount];
        int[][] matrix = new int[deliveriesCount + 1][deliveriesCount + 1];
        for (int i = 0; i < deliveriesCount; i++) {
            deadlines[i] = scanner.nextInt();
        }
        for (int i = 0; i < deliveriesCount + 1; i++) {
            for (int j = 0; j < deliveriesCount + 1; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return new BasicTask(deliveriesCount, deadlines, matrix);
    }

    public static OwnTask readOwnTaskFromFile(String filePath) {
        Scanner scanner = new Scanner(System.in);
        int deliveriesCount = scanner.nextInt();
        int[] deadlines = new int[deliveriesCount];
        int[][] matrix = new int[deliveriesCount + 1][deliveriesCount + 1];
        for (int i = 0; i < deliveriesCount; i++) {
            deadlines[i] = scanner.nextInt();
        }
        for (int i = 0; i < deliveriesCount + 1; i++) {
            for (int j = 0; j < deliveriesCount + 1; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return new OwnTask(deliveriesCount, deadlines, matrix);
    }

    public static <T> boolean includes(CyclicArray<T> array, T elem) {
        for (int i = 0; i < array.size(); i++) {
            if (elem == array.get(i)) {
                return true;
            }
        }
        return false;
    }
    
    public static <T> boolean includes(T[] array, T elem) {
        for (T t : array) {
            if (t == elem) {
                return true;
            }
        }
        return false;
    }

    public static int argmin(CyclicArray<Integer> array) {
        int argMin = 0;
        Integer min = array.get(0);
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) < min) {
                min = array.get(i);
                argMin = i;
            }
        }
        return argMin;
    }
}
