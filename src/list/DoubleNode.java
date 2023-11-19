package list;

/**
 * A generic node class for a doubly linked list.
 */
public class DoubleNode<T> {
    private T item; // The item stored in the node
    private DoubleNode<T> next; // Reference to the next node in the list
    private DoubleNode<T> prev; // Reference to the previous node in the list

    /**
     * Default constructor creating an empty node.
     */
    public DoubleNode(){
        this(null, null, null);
    }

    /**
     * Constructor creating a node with a given item and null references for next and prev.
     * 
     * @param anyItem The item to store in the node.
     */
    public DoubleNode(T anyItem){
        this(anyItem, null, null);
    }

    /**
     * Constructor creating a node with specified item, next, and prev references.
     * 
     * @param item The item to store in the node.
     * @param next The reference to the next node in the list.
     * @param prev The reference to the previous node in the list.
     */
    public DoubleNode(T item, DoubleNode<T> next, DoubleNode<T> prev) {
        this.item = item;
        this.next = next;
        this.prev = prev;
    }

    /**
     * Gets the item stored in the node.
     * 
     * @return The item stored in the node.
     */
    public T getItem() {
        return item;
    }

    /**
     * Sets or updates the item stored in the node.
     * 
     * @param item The new item to store in the node.
     */
    public void setItem(T item) {
        this.item = item;
    }

    /**
     * Gets the reference to the next node in the list.
     * 
     * @return The next node.
     */
    public DoubleNode<T> getNext() {
        return next;
    }

    /**
     * Sets or updates the reference to the next node in the list.
     * 
     * @param next The new next node.
     */
    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }

    /**
     * Gets the reference to the previous node in the list.
     * 
     * @return The previous node.
     */
    public DoubleNode<T> getPrev() {
        return prev;
    }

    /**
     * Sets or updates the reference to the previous node in the list.
     * 
     * @param prev The new previous node.
     */
    public void setPrev(DoubleNode<T> prev) {
        this.prev = prev;
    }
}
