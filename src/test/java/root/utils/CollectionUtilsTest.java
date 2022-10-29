package root.utils;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import root.collection.Array;

public class CollectionUtilsTest {
    private Array<Integer> array;

    @BeforeEach
    public void initArray() {
        array = new Array<>(5, 1);
        for (int i = array.getBegin(); i < array.getEnd(); i++) {
            array.set(i, i - array.getBegin() + 3);
        }
    }

    @Test
    public void subtractArrayTest() {
        Array<Integer> sub = new Array<>(2, 0);
        sub.set(0, 4);
        sub.set(1, 6);
        Array<Integer> expected = new Array<>(Arrays.asList(3, 5, 7), 1);

        Assertions.assertEquals(expected, CollectionUtils.subtract(array, sub));
    }

    @Test
    public void appendArrayTest() {
        Array<Integer> expected = new Array<>(Arrays.asList(3, 4, 5, 6, 7, 13), 1);

        Assertions.assertEquals(expected, CollectionUtils.append(array, 13));
    }
}
