package root.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Array<T> implements Iterable<T> {
    private final List<T> list;
    private final int size;

    private final int begin;

    public Array(int size, int begin) {
        this.size = size;
        this.begin = begin;
        list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(null);
        }
    }

    public Array(Array<T> array) {
        this(array.getSize(), array.getBegin());
        for (int i = getBegin(); i < getEnd(); i++) {
            set(i, array.get(i));
        }
    }

    public Array(Array<T> array, int size) {
        this(size, array.getBegin());
        for (int i = array.getBegin(); i < array.getSize(); i++) {
            set(i, array.get(i));
        }
    }

    public Array(List<T> list, int begin) {
        this(list.size(), begin);
        for (int i = getBegin(); i < getEnd(); i++) {
            set(i, list.get(i - getBegin()));
        }
    }

    public List<T> toList() {
        List<T> list = new ArrayList<>(getSize());
        for (T t : this) {
            list.add(t);
        }
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Array<?> array = (Array<?>) o;
        if (size != array.size || begin != array.begin) return false;
        for (int i = getBegin(); i < getEnd(); i++) {
            if (!Objects.equals(get(i), array.get(i))) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(list, size, begin);
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

    public int getBegin() {
        return this.begin;
    }

    public int getEnd() {
        return getBegin() + getSize();
    }

    @Override
    public String toString() {
        List<String> values = new ArrayList<>(getSize());
        for (T value : this) {
            values.add(null == value ? "null" : value.toString());
        }
        return "[" + String.join(", ", values) + "]";
    }

    private class Iterator implements java.util.Iterator<T> {
        private int next;
        private Array<T> array;

        public Iterator(Array<T> array) {
            this.array = array;
            this.next = array.getBegin();
        }

        @Override
        public boolean hasNext() {
            return next < array.getEnd();
        }

        @Override
        public T next() {
            return this.array.get(next++);
        }
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new Iterator(this);
    }
}
