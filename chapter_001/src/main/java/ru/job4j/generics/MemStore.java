package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        T repObj = findById(id);
        if(repObj == null) return false;
        mem.set(mem.indexOf(repObj), model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        T delObj = findById(id);
        if(delObj == null) return false;
        mem.remove(delObj);
        return true;
    }

    @Override
    public T findById(String id) {
        return mem.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}