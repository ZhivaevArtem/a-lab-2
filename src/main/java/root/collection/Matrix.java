package root.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Matrix<T> {
    private final Array<Array<T>> matrix;

    private final int width;
    private final int height;

    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new Array<>(height);
        for (int i = 0; i < height; i++) {
            this.matrix.set(i, new Array<>(width));
        }
    }

    public Matrix(Matrix<T> matrix, int startFromI, int startFromJ) {
        this(matrix.width, matrix.height);
        for (int i = 0; i < matrix.height; i++) {
            for (int j = 0; j < matrix.width; j++) {
                this.set(startFromI + i, startFromJ + j, matrix.get(startFromI + i, startFromJ + j));
            }
        }
    }

    public T get(int i, int j) {
        return this.matrix.get(i).get(j);
    }

    public void set(int i, int j, T value) {
        this.matrix.get(i).set(j, value);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    public String toString() {
        List<String> rows = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            List<String> values = new ArrayList<>(width);
            for (int j = 0; j < width; j++) {
                T value = get(i, j);
                values.add(value == null ? "null" : value.toString());
            }
            rows.add(String.join(", ", values));
        }
        return String.join("\n", rows);
    }
}
