/**
 * A class representing a singly linked list.
 *
 * @param <T> The type of data stored in the nodes.
 */
public class LinkedList<T> {
    Node<T> head;
    int length;
    Node<T> tail;

    public LinkedList() {
        head = null;
        length = 0;
        tail = head;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Node<T> getList() {
        return head;
    }

    public void addNode(Node<T> aNode) {
        if (isEmpty()) {
            head = aNode;
            tail = head;
        } else {
            tail.nextNode = aNode;
            tail = tail.nextNode;
            tail.nextNode = null;
        }
        length++;
    }

    public T removeNode() {
        if (isEmpty()) {
            // The list is empty, nothing to remove
            return null;
        }

        T data = head.getData();
        head = head.nextNode;

        // Update the tail reference if the list is now empty
        if (head == null) {
            tail = null;
        }

        length--;

        return data;
    }

    public void clear() {
        head = null;
        tail = null;
        length = 0;
    }

    // ... other methods

    /**
     * Prints the current linked list.
     */
    public void printList() {
        Node<T> current = head;
        System.out.print("List: ");


        System.out.println(); // Print a new line after the list is printed
    }
}
