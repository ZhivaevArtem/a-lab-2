package root.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TriangularMatrixTest {
    private TriangularMatrix<String> matrix;

    @BeforeEach
    public void initMatrix() {
        matrix = new TriangularMatrix<>(31);
        for (int i = 0; i < matrix.getSize(); i++) {
            for (int j = i; j < matrix.getSize(); j++) {
                matrix.set(i, j, i + ":" + j);
            }
        }
    }

    @Test
    public void symmetricAccessGivesSameElements() {
        Assertions.assertEquals(matrix.get(1, 3), matrix.get(3, 1));
    }
}
