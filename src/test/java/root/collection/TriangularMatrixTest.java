package root.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TriangularMatrixTest {
    private TriangularMatrix<String> matrix;

    @BeforeEach
    public void initMatrix() {
        matrix = new TriangularMatrix<>(2, 1);
        for (int i = matrix.getBegin(); i < matrix.getEnd(); i++) {
            for (int j = i; j < matrix.getEnd(); j++) {
                matrix.set(i, j, i + ":" + j);
            }
        }
    }

    @Test
    public void symmetricAccessGivesSameElements() {
        Assertions.assertEquals(matrix.get(1, 3), matrix.get(3, 1));
    }
}
