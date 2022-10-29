package root.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MathUtilsTest {
    @Test
    public void positiveMod() {
        Assertions.assertEquals(3, MathUtils.positiveMod(-12, 5));
    }
}
