package root.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatrixTest {
    private Matrix<Integer> matrix;
    private int startsFromI;
    private int startsFromJ;

    @BeforeEach
    public void initMatrix() {
        startsFromI = 11;
        startsFromJ = 13;
        matrix = new Matrix<>(17, 29);
        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++) {
                matrix.set(startsFromI + i, startsFromJ + j, i + j);
            }
        }
    }

    @Test
    public void cyclicAccessTest() {
        Assertions.assertEquals(8, matrix.get(startsFromI + 3, startsFromJ + 5));
    }
}
