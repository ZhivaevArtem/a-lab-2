package root.collection;

public class SquareMatrix<T> extends Matrix<T> {
    public SquareMatrix(int size, int begin) {
        super(size, begin, size, begin);
    }

    public int getSize() {
        return super.getHeight();
    }

    public int getBegin() {
        return getBeginI();
    }

    public int getEnd() {
        return getEndI();
    }
}
