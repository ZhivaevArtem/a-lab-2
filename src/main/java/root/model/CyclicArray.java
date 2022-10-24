package root.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class CyclicArray<T> implements Iterable<T> {
    private class CyclicArrayIterator implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index >= size();
        }

        @Override
        public T next() {
            return get(this.index++);
        }
    }

    private List<T> array;
    private int size;

    public CyclicArray(int size) {
        this.array = new ArrayList<>(size);
        this.size = size;
    }

    public T get(int i) {
        return this.array.get(this.inboundIndex(i));
    }

    public void set(int i, T value) {
        this.array.set(this.inboundIndex(i), value);
    }

    public int size() {
        return this.size;
    }

    private int inboundIndex(int i) {
        while (i < 0) {
            i += this.size;
        }
        while (i >= this.size) {
            i -= this.size;
        }
        return i;
    }

    @Override
    public Iterator<T> iterator() {
        return new CyclicArrayIterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }
}
