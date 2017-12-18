package queue;

/**
 * A collection based on the first-in-first-out policy.
 *
 * @param <E> the type of elements in this queue
 */
public interface Queue<E> extends Iterable<E> {

    /**
     * Add an item.
     */
    void enqueue(E element);

    /**
     * Remove the least recently added (aka oldest) item.
     */
    E dequeue();

    /**
     * Is the queue empty? Returns true if so, false otherwise.
     */
    boolean isEmpty();

    /**
     * Number of elements in the queue.
     */
    int size();
}
