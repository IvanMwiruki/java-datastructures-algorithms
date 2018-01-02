package symboltable;

/**
 * Keys are not ordered.
 */
public interface UnorderedSymbolTable<K, V> {

    /**
     * Put this key value pair into the table.
     * Remove key from table if value is null.
     */
    void put(K key, V value);

    /**
     * Return value that is paired with this key.
     * Or null if key is absent.
     */
    V get(K key);

    /**
     * Removes key and its value from symbol table.
     */
    void delete(K key);

    /**
     * Is there a value paired with this key? (non null value, that is)
     */
    boolean contains(K key);

    /**
     * Is the symbol table empty?
     */
    boolean isEmpty();

    /**
     * Number of key-value pairs in the symbol table.
     */
    int size();

    /**
     * All the keys in the symbol table.
     */
    Iterable<K> keys();
}
