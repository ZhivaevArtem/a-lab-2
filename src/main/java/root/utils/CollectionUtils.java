package root.utils;

import java.util.ArrayList;
import java.util.List;
import root.collection.Array;

public class CollectionUtils {
    public static <T> Array<T> subtract(Array<T> array1, Array<T> array2) {
        List<T> list = array1.toList();
        for (T t : array2) {
            list.remove(t);
        }
        return new Array<>(list, array1.getBegin());
    }

    public static <T> Array<T> append(Array<T> array, T t) {
        List<T> list = array.toList();
        list.add(t);
        return new Array<>(list, array.getBegin());
    }

    public static Array<Integer> range(int start, int end, int begin) {
        List<Integer> list = new ArrayList<>(end - start);
        for (int i = start; i < end; i++) {
            list.add(i);
        }
        return new Array<>(list, begin);
    }
}
