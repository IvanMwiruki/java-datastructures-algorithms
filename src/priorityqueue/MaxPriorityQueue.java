package priorityqueue;

/**
 * A collection that supports getting the largest element.
 */
public interface MaxPriorityQueue<E extends Comparable<E>> {

    /**
     * Insert an element.
     */
    void insert(E element);

    /**
     * Return the largest element.
     */
    E max();

    /**
     * Return and remove the largest element.
     */
    E deleteMax();

    /**
     * Is the queue empty? Returns true if so, false otherwise.
     */
    boolean isEmpty();

    /**
     * Number of elements in the queue.
     */
    int size();
}
