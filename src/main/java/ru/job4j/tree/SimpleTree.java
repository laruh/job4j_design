package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    private Optional<Node> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node> rsl = Optional.empty();
        return rsl;
    }

    public boolean isBinary() {
        return false;
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> opParent = findBy(parent);
        return opParent.isPresent()
                && findBy(child).isEmpty()
                && opParent.get().getChildren().add(new Node<>(child));
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.getValue().equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.getChildren());
        }
        return rsl;
    }

    public Node<E> getRoot() {
        return root;
    }
}
