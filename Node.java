/**
 * A class representing a node in a linked list.
 * @param <T> The type of data stored in the node.
 */
class Node<T> {
    private T data;
    protected Node<T> nextNode;  // Change to protected

    public Node() {
        nextNode = null;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public Node<T> getNextNode() {
        return this.nextNode;
    }

    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }
}
