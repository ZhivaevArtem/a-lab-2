package root.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Matrix<T> {
    private final Array<Array<T>> matrix;

    private final int width;
    private final int height;
    private final int beginI, beginJ;

    public Matrix(int height, int beginI, int width, int beginJ) {
        this.width = width;
        this.height = height;
        this.beginI = beginI;
        this.beginJ = beginJ;
        this.matrix = new Array<>(height, beginI);
        for (int i = getBeginI(); i < getEndI(); i++) {
            this.matrix.set(i, new Array<>(width, beginJ));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix<?> matrix1 = (Matrix<?>) o;
        return width == matrix1.width && height == matrix1.height && beginI == matrix1.beginI && beginJ == matrix1.beginJ && matrix.equals(matrix1.matrix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matrix, width, height, beginI, beginJ);
    }

    public Matrix(Matrix<T> matrix) {
        this(matrix.height, matrix.getBeginI(), matrix.width, matrix.getBeginJ());
        for (int i = getBeginI(); i < getEndI(); i++) {
            for (int j = getBeginJ(); j < getEndJ(); j++) {
                set(i, j, matrix.get(i, j));
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

    public int getBeginI() {
        return beginI;
    }

    public int getBeginJ() {
        return beginJ;
    }

    public int getEndI() {
        return getBeginI() + getHeight();
    }

    public int getEndJ() {
        return getBeginJ() + getWidth();
    }

    @Override
    public String toString() {
        List<String> rows = new ArrayList<>(height);
        for (Array<T> row : matrix) {
            List<String> values = new ArrayList<>(width);
            for (T value : row) {
                values.add(null == value ? "null" : value.toString());
            }
            rows.add("[" + String.join(", ", values) + "]");
        }
        return String.join("\n", rows);
    }
}
