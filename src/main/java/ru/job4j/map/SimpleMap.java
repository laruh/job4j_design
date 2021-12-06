package ru.job4j.map;

import java.util.Iterator;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        return false;
    }

    private int hash(K key) {
        int hk = (key == null) ? 0 : key.hashCode();
        return hk == 0 ? 0 : hk ^ (hk >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {

    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final MapEntry<K, V>[] storage = table;
            private int cursor = 0;
            private  final  int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = 0; i < storage.length; i++) {
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
