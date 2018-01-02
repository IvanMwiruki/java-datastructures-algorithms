package symboltable;

/**
 * Keys are ordered.
 */
public interface OrderedSymbolTable<K extends Comparable<K>, V> extends UnorderedSymbolTable<K, V> {

    /**
     * Returns the smallest key.
     */
    K min();

    /**
     * Returns the largest key.
     */
    K max();

    /**
     * Returns largest key less than or equal to given key.
     */
    K floor(K key);

    /**
     * Returns smallest key larger than or equal to given key.
     */
    K ceiling(K key);

    /**
     * Number of keys less than given key.
     */
    int rank(K key);

    /**
     * Key with given rank.
     */
    K select(int k);

    /**
     * Delete the smallest key.
     */
    void deleteMin();

    /**
     * Delete the largest key.
     */
    void deleteMax();

    /**
     * Number of keys in [low ... high]
     */
    int size(K low, K high);

    /**
     * Keys in [low ... high] in sorted order.
     */
    Iterable<K> keys(K low, K high);
}
