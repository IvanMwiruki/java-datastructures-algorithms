package symboltable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SeparateChainingHashSymbolTable<K, V> implements UnorderedSymbolTable<K, V> {

    private int size; // number of key value pairs
    private int arraySize; // hash table size (size of array that backs hash table)
    private final UnorderedLinkedListSequentialSearchSymbolTable<K, V>[] symbolTables; // array of symbol tables for separate chaining

    public SeparateChainingHashSymbolTable() {
        this(997);
    }

    public SeparateChainingHashSymbolTable(int arraySize) {
        // create linked lists
        this.arraySize = arraySize;
        symbolTables = (UnorderedLinkedListSequentialSearchSymbolTable<K, V>[])
                new UnorderedLinkedListSequentialSearchSymbolTable[arraySize];

        for (int i = 0; i < arraySize; i++) {
            symbolTables[i] = new UnorderedLinkedListSequentialSearchSymbolTable<>();
        }
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

        symbolTables[hash(key)].put(key, value);
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        return symbolTables[hash(key)].get(key);
    }

    @Override
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        symbolTables[hash(key)].delete(key);
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
        Set<K> keys = new HashSet<>();

        for (int i = 0; i < arraySize; i++) {
            keys.addAll((Collection) symbolTables[i].keys());
        }

        return keys;
    }
}
