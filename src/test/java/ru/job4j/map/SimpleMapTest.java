package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleMapTest {

    @Test
    public void whenPutThenGet() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        for (int i = 0; i < 5; i++) {
            map.put(i, i);
        }
        for (int i = 0; i < 5; i++) {
            assertThat(map.get(i), is(i));
        }
    }

    @Test
    public void whenDontHaveKey() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        assertNull(map.get(3));
    }

    @Test
    public void whenGetNull() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        assertNull(map.get(null));
    }

    @Test
    public void whenKeysAreNotEqual() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(5, 5);
        assertFalse(map.remove(2));
    }

    @Test
    public void whenRemove() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(2, 2);
        assertTrue(map.remove(2));
        assertFalse(map.remove(2));
        assertNull(map.get(2));
    }

    @Test
    public void whenResizeTable() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        for (int i = 0; i < 30; i++) {
            map.put(i, i);
        }
        for (int i = 0; i < 30; i++) {
            assertThat(map.get(i), is(i));
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenHasNextExceptionWithPut() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        Iterator<Integer> it = map.iterator();
        map.put(2, 2);
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElementException() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        Iterator<Integer> it = map.iterator();
        it.next();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenHasNextExceptionWithRemove() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        map.put(1, 1);
        Iterator<Integer> it = map.iterator();
        map.remove(1);
        it.next();
    }
}