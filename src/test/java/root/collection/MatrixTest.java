package root.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatrixTest {
    private Matrix<Integer> matrix;

    @BeforeEach
    public void initMatrix() {
        matrix = new Matrix<>(29, 13, 17, 11);
        for (int i = matrix.getBeginI(); i < matrix.getEndI(); i++) {
            for (int j = matrix.getBeginJ(); j < matrix.getEndJ(); j++) {
                matrix.set(i, j, i + j - matrix.getBeginI() - matrix.getBeginJ());
            }
        }
    }

    @Test
    public void manualCopyTest() {
        Matrix<Integer> matrix = new Matrix<>(this.matrix.getHeight(), this.matrix.getBeginI(), this.matrix.getWidth(), this.matrix.getBeginJ());
        for (int i = this.matrix.getBeginI(); i < this.matrix.getEndI(); i++) {
            for (int j = this.matrix.getBeginJ(); j < this.matrix.getEndJ(); j++) {
                matrix.set(i, j, this.matrix.get(i, j));
            }
        }
        Assertions.assertEquals(this.matrix, matrix);
    }

    @Test
    public void copyConstructorTest() {
        Matrix<Integer> matrix = new Matrix<>(this.matrix);
        Assertions.assertEquals(this.matrix, matrix);
    }

    @Test
    public void cyclicAccessTest() {
        Assertions.assertEquals(8, matrix.get(matrix.getBeginI() + 3, matrix.getBeginJ() + 5));
    }
}
