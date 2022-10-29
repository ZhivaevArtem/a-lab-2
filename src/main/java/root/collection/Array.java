package root.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Array<T> {
    private final List<T> list;
    private final int size;

    public Array(int size) {
        this.size = size;
        list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(null);
        }
    }

    private int getIndex(int index) {
        return ((index % this.size) + this.size) % this.size;
    }

    public T get(int index) {
        return this.list.get(this.getIndex(index));
    }

    public void set(int index, T value) {
        this.list.set(this.getIndex(index), value);
    }

    public int getSize() {
        return this.size;
    }

    public void copyFrom(Array<T> array, int copyFrom, int copyTo) {
        for (int i = 0; i < array.getSize(); i++) {
            set(copyTo + i, array.get(copyFrom + i));
        }
    }

    public boolean equals(Array<T> array) {
        if (getSize() != array.getSize()) return false;
        for (int i = 0; i < size; i++) {
            if (this.get(i) != array.get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + this.list.stream().map(t -> null == t ? "null" : t.toString()).collect(Collectors.joining(", ")) + "]";
    }
}
