package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Iterable<K> {
    private Node<K, V>[] table;
    private int capacity;
    private final float maxLoad = 0.75f;
    private int size;
    private int modCount = 0;

    @SuppressWarnings("unchecked")
    public SimpleMap() {
        capacity = 16;
        size = 0;
        table = (Node<K, V>[]) new Node[capacity];
    }

    public boolean insert(K key, V value) {
        Node<K, V> elem = new Node<>(hash(key), key, value);
        int i = index(key);
        if (table[i] != null) {
            return false;
        }
        table[i] = elem;
        if(++size >= (int)(capacity * maxLoad)) {
            resize();
        }
        modCount++;
        return true;
    }

    public V get(K key) {
        if(nullElement(key)) {
            throw new NoSuchElementException();
        }
        return table[index(key)].value;
    }

    public boolean delete(K key) {
        if(nullElement(key)) {
            return false;
        }
        table[index(key)] = null;
        modCount++;
        return true;
    }

    private boolean nullElement(K key) {
        int i = index(key);
        if(table[i] == null) {
            return true;
        }
        return false;
    }

    private int index(K key) {
        return hash(key) & (capacity - 1);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] oldTab = table;
        capacity *= 2;
        table = (Node<K, V>[]) new Node[capacity];
        for (Node<K, V> elem : oldTab) {
            if (elem != null) {
                table[elem.hash & (capacity - 1)] = elem;
            }
        }
    }

    private int hash(K key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ (h >> 16);
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expModCount = modCount;
            int index = 0;
            @Override
            public boolean hasNext() {
                if(expModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if(index == capacity) {
                    return false;
                }
                if(table[index] == null) {
                    index++;
                    return hasNext();
                }
                return true;
            }

            @Override
            public K next() {
                if(!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            Node<K, V> elem = table[i];
            if (elem != null) {
                result.append("[")
                        .append(elem.key)
                        .append(": ")
                        .append(elem.value)
                        .append("]")
                        .append("\n");
            }
        }
        return result.toString();
    }

    public int getCapacity() {
        return capacity;
    }

    private static class Node<K, V> {
        private final int hash;
        private final K key;
        private V value;

        public Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }
}
