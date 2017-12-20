package queue;

/**
 * A collection that supports adding and removing from both ends.
 */
public interface Deque<E> extends Iterable<E> {

    /**
     * Add an element to the left end.
     */
    void pushLeft(E element);

    /**
     * Add an element to the right end.
     */
    void pushRight(E element);

    /**
     * Remove an item from the left end.
     */
    E popLeft();

    /**
     * Remove an item from the right end.
     */
    E popRight();

    /**
     * Is the deque empty? Returns true if so, false otherwise.
     */
    boolean isEmpty();

    /**
     * Number of elements in the deque.
     */
    int size();
}
