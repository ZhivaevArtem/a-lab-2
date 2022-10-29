package root.collection;

import java.util.ArrayList;
import java.util.List;

public class TriangularMatrix<T> extends SquareMatrix<T> {
    public TriangularMatrix(int size, int begin) {
        super(size, begin);
    }

    @Override
    public T get(int i, int j) {
        if (j < i) {
            return super.get(j, i);
        }
        return super.get(i, j);
    }

    @Override
    public void set(int i, int j, T value) {
        super.set(j, i, value);
        super.set(i, j, value);
    }

    @Override
    public String toString() {
        List<String> rows = new ArrayList<>(getSize());
        for (int i = 0; i < getSize(); i++) {
            List<String> values = new ArrayList<>(getSize());
            for (int j = 0; j < getSize(); j++) {
                T value = get(i, j);
                values.add(null == value ? "null" : value.toString());
            }
            rows.add(String.join(", ", values));
        }
        return String.join("\n", rows);
    }
}
