package symboltable;

import java.util.HashSet;
import java.util.Set;

/**
 * A linked list implementation of a symbol table.
 */
public class UnorderedLinkedListSequentialSearchSymbolTable<K, V> implements UnorderedSymbolTable<K, V> {

    private Node first;
    private int size;

    public UnorderedLinkedListSequentialSearchSymbolTable() {}

    private class Node {
        K key;
        V value;
        Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public void put(K key, V value) { // search for key. Update value if found; grow table if not
        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (value == null) {
            delete(key);
        }

        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value; // search hit. Update value
                return;
            }
        }
        first = new Node(key, value, first); // search miss. Add new node
        size++;
    }

    @Override
    public V get(K key) { // search for key, return associated value
        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (isEmpty()) {
            return null;
        }

        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.value; // search hit
            }
        }
        return null; // search miss
    }

    @Override
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        for (Node x = first; x != null && x.next != null; x = x.next) {
            if (key.equals(x.next.key)) {
                x.next = x.next.next;
                size--;
                return;
            }
        }
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
        Set<K> keys = new HashSet<>(size);
        for (Node x = first; x != null; x = x.next) {
            keys.add(x.key);
        }
        return keys;
    }
}
