/**
 * @param <T> The type of elements in the queue.
 */
public class Queue<T> {
    private LinkedList<T> queueList = new LinkedList<>();

    /**
     * Adds a new entry to the back of this queue.
     *
     * @param newEntry An object to be added.
     */
    public void enqueue(T newEntry) {
        Node<T> newNode = new Node<>();
        newNode.setData(newEntry);
        queueList.addNode(newNode);
    }

    /**
     * Removes and returns the entry at the front of this queue.
     *
     * @return The object at the front of the queue.
     * @throws EmptyQueueException if the queue is empty before the operation.
     */
    public T dequeue() {
        return queueList.removeNode(); // Implement removeNode in your LinkedList class
    }

    /**
     * Checks if the queue is empty.
     *
     * @return True if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return queueList.isEmpty();
    }

    /**
     * Removes all entries from this queue.
     */
    public void clear() {
        queueList.clear();
    }

    /**
     * Prints the current Queue
     */
    public void printQueue() {
        queueList.printList();
    }
}
