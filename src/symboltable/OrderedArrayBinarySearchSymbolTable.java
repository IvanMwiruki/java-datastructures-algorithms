package symboltable;

import queue.LinkedQueue;
import queue.Queue;

/**
 * An array implementation of a symbol table.
 */
public class OrderedArrayBinarySearchSymbolTable<K extends Comparable<K>, V> implements OrderedSymbolTable<K, V> {

    private K[] keys;
    private V[] values;
    private int size;

    public OrderedArrayBinarySearchSymbolTable(int capacity) {
        keys = (K[]) new Comparable[capacity];
        values = (V[]) new Object[capacity];
    }

    @Override
    public K min() {
        return keys[0];
    }

    @Override
    public K max() {
        return keys[size - 1];
    }

    @Override
    public K floor(K key) {
        int rank = rank(key);
        return keys[rank - 1];
    }

    @Override
    public K ceiling(K key) {
        int rank = rank(key);
        return keys[rank];
    }

    @Override
    public int rank(K key) { // binary search
        if (key == null) {
            throw new IllegalArgumentException();
        }

        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = low + ((high - low) / 2);
            int compare = key.compareTo(keys[mid]);

            if (compare < 0) {
                high = mid - 1;
            } else if (compare > 0) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return low;
    }

    @Override
    public K select(int k) {
        if (k < 0 || k > size - 1) {
            throw new IllegalArgumentException();
        }

        return keys[k];
    }

    @Override
    public void deleteMin() {
        delete(min());
    }

    @Override
    public void deleteMax() {
        delete(max());
    }

    @Override
    public int size(K low, K high) {
        if (low == null || high == null) {
            throw new IllegalArgumentException();
        }

        if (high.compareTo(low) < 0) {
            return 0;
        } else if (contains(high)) {
            return rank(high) - rank(low) + 1;
        } else {
            return rank(high) - rank(low);
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

        if (size == keys.length) {
            resize(2 * keys.length);
        }

        int rank = rank(key);
        if (rank < size && keys[rank].compareTo(key) == 0) {
            values[rank] = value; // search hit. Update value
            return;
        }

        for (int i = size; i > rank; i--) { // move all keys/ values greater than this key -> 1 to the right. Then insert this key/ value
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
        keys[rank] = key;
        values[rank] = value;
        size++;
    }

    @Override
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (get(key) != null) {
            int rank = rank(key);

            for (int i = size - 1; i >= rank; i--) {
                keys[i - 1] = keys[i];
                values[i - 1] = values[i];
            }
            size--;
        }
    }

    private void resize(int capacity) {
        K[] newKeys = (K[]) new Comparable[capacity];
        for (int i = 0; i < size; i++) {
            newKeys[i] = keys[i];
        }
        keys = newKeys;

        V[] newValues = (V[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newValues[i] = values[i];
        }
        values = newValues;
    }

    @Override
    public V get(K key) { // search for key, return associated value
        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (isEmpty()) {
            return null;
        }

        int rank = rank(key);
        if (rank < size && keys[rank].compareTo(key) == 0) { // search hit
            return values[rank];
        } else {
            return null; // search miss
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
    public Iterable<K> keys(K low, K high) {
        Queue<K> keyqueue = new LinkedQueue<>();
        for (int i = rank(low); i < rank(high); i++) {
            keyqueue.enqueue(keys[i]);
            if (contains(high)) {
                keyqueue.enqueue(keys[rank(high)]);
            }
        }
        return keyqueue;
    }

    @Override
    public Iterable<K> keys() {
        return keys(min(), max());
    }
}
