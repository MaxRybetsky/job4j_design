package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkListIterator<E> implements Iterator<E> {
    private LinkList<E> linkedList;
    private int index = 0;
    private int modCount;

    public LinkListIterator(LinkList<E> linkedList) {
        this.linkedList = linkedList;
        this.modCount = linkedList.getModCount();
    }

    @Override
    public boolean hasNext() {
        if(isModified()) {
            throw new ConcurrentModificationException();
        }
        return index < linkedList.size();
    }

    @Override
    public E next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        return linkedList.get(index++);
    }

    private boolean isModified() {
        return modCount != linkedList.getModCount();
    }
}
