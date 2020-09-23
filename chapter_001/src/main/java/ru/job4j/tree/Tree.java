package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    public Tree(Node<E> root) {
        this.root = root;
    }

    @Override
    public boolean add(E parent, E child) {
        Node<E> parentNode = findBy(parent).orElse(null);
        if (parentNode == null) {
            throw new NoSuchElementException();
        }
        if (findBy(child).isPresent()) {
            return false;
        }
        return parentNode.children.add(new Node<>(child));
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return reqElement(el -> el.value.equals(value));
    }

    public boolean isBinary() {
        return !reqElement(el -> el.children.size() > 2).isPresent();
    }

    private Optional<Node<E>> reqElement(Predicate<Node<E>> predicate) {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (predicate.test(el)) {
                return Optional.of(el);
            }
            data.addAll(el.children);
        }
        return Optional.empty();
    }
}