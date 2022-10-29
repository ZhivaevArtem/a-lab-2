package root.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayTest {
    private Array<Integer> array;

    @BeforeEach
    public void initArray() {
        array = new Array<>(11, 17);
        for (int i = 0; i < array.getSize(); i++) {
            array.set(array.getBegin() + i, i + 1);
        }
    }

    @Test
    public void cyclicAccessTest() {
        Assertions.assertEquals(3, array.get(array.getBegin() + 2));
    }

    @Test
    public void manualCopyTest() {
        Array<Integer> array = new Array<>(this.array.getSize(), this.array.getBegin());
        for (int i = array.getBegin(); i < array.getEnd(); i++) {
            array.set(i, this.array.get(i));
        }
        Assertions.assertEquals(this.array, array);
    }

    @Test
    public void copyConstructorTest() {
        Array<Integer> array = new Array<>(this.array);
        Assertions.assertEquals(this.array, array);
    }

    @Test
    public void longerArrayEqualTest() {
        Array<Integer> array = new Array<>(this.array.getSize() + 3, 1);
        for (int i = this.array.getBegin(); i < this.array.getEnd(); i++) {
            array.set(i, this.array.get(i));
        }
        Assertions.assertEquals(this.array.get(this.array.getBegin() + 1), array.get(this.array.getBegin() + 1));
    }
}
