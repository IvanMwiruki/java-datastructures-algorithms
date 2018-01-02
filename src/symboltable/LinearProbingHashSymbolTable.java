package symboltable;

import java.util.Arrays;
import java.util.HashSet;

public class LinearProbingHashSymbolTable<K, V> implements UnorderedSymbolTable<K, V> {
    private int size; // number of key value pairs
    private int arraySize; // hash table size (size of arrays that back hash table)
    private K[] keys;
    private V[] values;


    public LinearProbingHashSymbolTable(int capacity) {
        this.arraySize = capacity;
        this.keys = (K[]) new Object[capacity];
        this.values = (V[]) new Object[capacity];
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % arraySize;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (value == null) {
            delete(key);
        }

        if (size >= arraySize / 2) {
            resize(2 * arraySize);
        }

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % arraySize) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        size++;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        for (int i = hash(key); keys[i] != null ; i = (i + 1) % arraySize) {
            if (keys[i].equals(key))
                return values[i];
        }
        return null;
    }

    @Override
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (!contains(key)) {
            return;
        }

        int i = hash(key);
        while(!key.equals(keys[i])) {
            i = (i + 1) % arraySize;
        }

        keys[i] = null;
        values[i] = null;

        i = (i + 1) % arraySize;
        while (keys[i] != null) {
            K keyToRedo = keys[i];
            V valueToRedo = values[i];
            keys[i] = null;
            values[i] = null;
            size--;
            put(keyToRedo, valueToRedo);

            i = (i + 1) % arraySize;
        }

        size--;
        if (size > 0 && size <= arraySize / 8) {
            resize(arraySize / 2);
        }
    }

    private void resize(int max) {
        LinearProbingHashSymbolTable<K, V> newTable = new LinearProbingHashSymbolTable<>(max);

        for (int i = 0; i < arraySize; i++) {
            if (keys[i] != null) {
                newTable.put(keys[i], values[i]);
            }
        }

        keys = newTable.keys;
        values = newTable.values;
        size = newTable.size;
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<K> keys() {
        return new HashSet<>(Arrays.asList(keys));
    }
}
