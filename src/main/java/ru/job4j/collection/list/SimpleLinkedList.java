package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private int size = 0;
    private int modCount = 0;
    private transient Node<E> first;
    private transient Node<E> last;

    private static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        private Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(E value) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl;
        if (index == 0) {
            return first.item;
        } else if (index == size) {
            return last.item;
        }
        if (index > size / 2) {
            rsl = first;
            for (int i = 0; i < index; i++) {
                rsl = rsl.next;
            }
        } else {
            rsl = last;
            for (int i = size - 1; i > index; i--) {
                rsl = rsl.prev;
            }
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> cursor = first;
            private  final  int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw  new NoSuchElementException();
                }
                E rsl = cursor.item;
                cursor = cursor.next;
                return rsl;
            }
        };
    }
}
