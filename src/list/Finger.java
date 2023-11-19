package list;

/**
 * A class representing a 'finger' pointing to a specific node in a doubly linked list.
 * It acts as a marker or pointer to a particular position in the list.
 */
public class Finger<T> {
    private DoubleNode<T> node; // The node to which the finger points
    private int idx; // The index or position of the node in the list

    /**
     * Constructor for creating a Finger object.
     * 
     * @param idx The index of the node in the list.
     * @param node The DoubleNode to which this Finger points.
     */
    public Finger(int idx, DoubleNode<T> node) {
        this.node = node;
        this.idx = idx;
    }

    /**
     * Gets the node to which this Finger points.
     * 
     * @return The DoubleNode pointed to by this Finger.
     */
    public DoubleNode<T> getNode() {
        return node;
    }

    /**
     * Sets or updates the node to which this Finger points.
     * 
     * @param node The new DoubleNode for this Finger to point to.
     */
    public void setNode(DoubleNode<T> node) {
        this.node = node;
    }

    /**
     * Gets the index of the node in the list.
     * 
     * @return The index of the node.
     */
    public int getIdx() {
        return idx;
    }

    /**
     * Sets or updates the index of the node in the list.
     * 
     * @param idx The new index of the node.
     */
    public void setIdx(int idx) {
        this.idx = idx;
    }
}
