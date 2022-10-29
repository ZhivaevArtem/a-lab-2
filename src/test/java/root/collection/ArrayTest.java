package root.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayTest {
    private Array<Integer> array;
    private int startsFrom;

    @BeforeEach
    public void initArray() {
        startsFrom = 17;
        array = new Array<>(11);
        for (int i = 0; i < array.getSize(); i++) {
            array.set(startsFrom + i, i + 1);
        }
    }

    @Test
    public void cyclicAccessTest() {
        Assertions.assertEquals(3, array.get(startsFrom + 2));
    }

    @Test
    public void arrayCopyFromFirstToFirstTest() {
        Array<Integer> array = new Array<>(this.array.getSize());
        array.copyFrom(this.array, 1, 1);
        Assertions.assertTrue(this.array.equals(array));
    }

    @Test
    public void longerArrayEqualTest() {
        Array<Integer> array = new Array<>(this.array.getSize() + 3);
        for (int i = 0; i < this.array.getSize(); i++) {
            array.set(startsFrom + i, this.array.get(startsFrom + i));
        }
        Assertions.assertEquals(this.array.get(startsFrom), array.get(startsFrom));
    }
}
