package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {
    private T[] container;
    private int size = 0;
    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    public void increaseCapacity() {
        if (size >= container.length) {
            container = Arrays.copyOf(container, size * 2);
        } else if (container.length == 0) {
            container = Arrays.copyOf(container, 10);
        }
    }

    @Override
    public void add(T value) {
        increaseCapacity();
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T temp = get(index);
        container[index] = newValue;
        return temp;
    }

    @Override
    public T remove(int index) {
        T temp = get(index);
        System.arraycopy(container,
                index + 1,
                container,
                index,
                size - 1 - index);
        container[size - 1] = null;
        modCount++;
        size--;
        return temp;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw  new ConcurrentModificationException();
                }
                return cursor < size && container[cursor] != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw  new NoSuchElementException();
                }
                return container[cursor++];
            }
        };
    }
}

