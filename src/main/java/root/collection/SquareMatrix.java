package root.collection;

public class SquareMatrix<T> extends Matrix<T> {
    public SquareMatrix(int size) {
        super(size, size);
    }

    public int getSize() {
        return super.getHeight();
    }
}
