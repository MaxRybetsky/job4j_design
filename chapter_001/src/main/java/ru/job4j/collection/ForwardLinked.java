package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> last;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            last = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        last = node;
    }

    public void deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        head = head.next;
    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T value = last.value;
        if (head == last) {
            head = null;
            last = null;
        } else {
            Node<T> current = head;
            while (current == last || current.next != last) {
                current = current.next;
            }
            last = current;
            last.next = null;
        }
        return value;
    }

    public void revert() {
        Node<T> current = head;
        Node<T> prev = null;
        while (current != null) {
            Node<T> temp = current.next;
            current.next = head == current ? null : prev;
            prev = current;
            current = temp;
        }
        head = prev;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}