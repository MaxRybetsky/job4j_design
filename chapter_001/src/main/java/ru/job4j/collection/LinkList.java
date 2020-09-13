package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkList<E> implements Iterable<E> {
    private Node<E> first;
    private Node<E> last;
    private int point;
    private int modCount;

    public LinkList() {
        this.first = null;
        this.last = null;
        this.point = 0;
        modCount = 0;
    }

    public void add(E value) {
        Node<E> elem = new Node<>(value);
        if (isEmpty()) {
            first = elem;
        } else {
            last.next = elem;
        }
        last = elem;
        point++;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, point);
        return node(index).value;
    }

    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkListIterator();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        Node<E> current = first;
        while (current != null) {
            res.append(current.value).append(" ");
            current = current.next;
        }
        return res.toString();
    }

    private Node<E> node(int index) {
        Node<E> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }

    private static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E value) {
            this.value = value;
        }
    }

    private class LinkListIterator implements Iterator<E> {
        private int index = 0;
        private final int expModCount = modCount;
        private Node<E> current = first;

        @Override
        public boolean hasNext() {
            if (isModified()) {
                throw new ConcurrentModificationException();
            }
            return index < point;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> res = current;
            current = current.next;
            index++;
            return res.value;
        }

        private boolean isModified() {
            return expModCount != modCount;
        }
    }
}
