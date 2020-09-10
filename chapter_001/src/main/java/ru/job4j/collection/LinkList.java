package ru.job4j.collection;

import java.util.Iterator;
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
        Node<E> elem = new Node<>(value, point++);
        if(isEmpty()) {
            first = elem;
        } else {
            last.next = elem;
        }
        last = elem;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, point);
        Node<E> current = first;
        while(current.index != index) {
            if(current.next == null) {
                return null;
            } else {
                current = current.next;
            }
        }
        return current.value;
    }

    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkListIterator<E>(this);
    }

    public int getModCount() {
        return modCount;
    }

    public int size() {
        return point;
    }

    @Override
    public String toString() {
        if(isEmpty()) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        Node<E> current = first;
        while(current != null) {
            res.append(current.value).append(" ");
            current = current.next;
        }
        return res.toString();
    }

    private class Node<E> {
        private E value;
        private Node<E> next;
        private int index;

        public Node(E value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
