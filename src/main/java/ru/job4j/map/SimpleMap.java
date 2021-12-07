package ru.job4j.map;

import java.util.Iterator;
import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((float) count / table.length >= LOAD_FACTOR) {
            expand();
        }
        boolean rsl = false;
        int i = indexFor(hash(key), table.length);
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(K key) {
        int hk = (key == null) ? 0 : key.hashCode();
        return hk == 0 ? 0 : hk ^ (hk >>> 16);
    }

    private int indexFor(int hash, int size) {
        return hash & (size - 1);
    }

    private void expand() {
        int newSize = getCapacity() * 2;
        MapEntry<K, V>[] temp = new MapEntry[newSize];
        for (MapEntry<K, V> node : table) {
            if (node != null) {
                temp[indexFor(hash(node.getKey()), newSize)] = node;
            }
        }
        this.table = temp;
        this.capacity = newSize;
    }

    @Override
    public V get(K key) {
        int i = indexFor(hash(key), table.length);
        return table[i] == null || !table[i].getKey().equals(key) ? null : table[i].getValue();
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = true;
        int i = indexFor(hash(key), table.length);
        if (table[i] == null || !table[i].getKey().equals(key)) {
            rsl = false;
        } else {
            table[i] = null;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final MapEntry<K, V>[] storage = table;
            private int cursor = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = cursor; i < storage.length; i++) {
                    if (storage[i] != null) {
                        cursor = i;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return storage[cursor++].getKey();
            }
        };
    }

    public int getCapacity() {
        return capacity;
    }

    static class MapEntry<K, V> {
        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "MapEntry{"
                    + "key=" + key
                    + ", value=" + value + '}';
        }
    }
}
