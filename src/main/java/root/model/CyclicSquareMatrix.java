package root.model;

public class CyclicSquareMatrix<T> {
    private CyclicArray<CyclicArray<T>> matrix;

    public CyclicSquareMatrix(int size) {
        this.matrix = new CyclicArray<>(size);
        for (int i = 0; i < size; i++) {
            this.matrix.set(i, new CyclicArray<>(size));
        }
    }

    public void set(int i, int j, T value) {
        this.matrix.get(i).set(j, value);
    }

    public T get(int i, int j) {
        return this.matrix.get(i).get(j);
    }

    public int size() {
        return this.matrix.size();
    }
}
