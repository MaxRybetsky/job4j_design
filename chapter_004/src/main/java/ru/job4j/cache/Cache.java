package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple cache for data storing.
 *
 * @param <T> type of objects to be saved in
 *            cache.
 */
public class Cache<T> {
    /**
     * Cache map buffer.
     */
    private final Map<String, SoftReference<T>> cache;

    /**
     * Simple constructor to create
     * cache buffer object.
     */
    public Cache() {
        cache = new HashMap<>();
    }

    /**
     * Checks if the object with key
     * is in the cache.
     *
     * @param key Object's key in cache
     * @return {@code true} if object with
     * the key is in cache, otherwise  - {@code false}
     */
    public boolean isInCache(String key) {
        return get(key) != null;
    }

    /**
     * Adds new object with key to cache if
     * there are no object with such key in
     * cache.
     *
     * @param key   Key of add object
     * @param value Object to add
     */
    public void add(String key, T value) {
        if (!isInCache(key)) {
            cache.put(key, new SoftReference<>(value));
        }
    }

    /**
     * Returns object with the key,
     * or {@code null} if there are
     * no such key in cache or value
     * of the key in cache is
     * is {@code null}.
     *
     * @param key Key of object to get
     * @return Object with specify key
     */
    public T get(String key) {
        if (!cache.containsKey(key)) {
            return null;
        }
        return cache.get(key).get();
    }

    /**
     * Returns a string representation
     * of cache with pair key-values
     * data.
     *
     * @return String representation
     * of cache
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        cache.forEach((k, v) -> result.append(k)
                .append(": ")
                .append(v.get())
                .append("\n"));
        return result.toString();
    }
}
