package list;

/**
 * A generic double linked list implementation.
 */
public class DoubleLinkedList<T> {
    private DoubleNode<T> head; // The head (first element) of the list
    private int itemCount; // The number of items currently in the list
    private Finger<T> finger; // A 'finger' pointing to a specific node, used for optimization

    /**
     * Constructor initializing an empty list.
     */
    public DoubleLinkedList() {
        clear();
    }

    /**
     * Checks if the list is empty.
     * 
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty(){
        return itemCount == 0;
    }

    /**
     * Returns the number of items in the list.
     * 
     * @return The number of items in the list.
     */
    public int getLength(){
        return itemCount;
    }

    /**
     * Inserts an entry at a specified position in the list.
     * 
     * @param position The position to insert the entry.
     * @param entry The entry to be inserted.
     * @return true if insertion is successful, false otherwise.
     */
    public boolean insert(int position, T entry){
        if (position >= 1 && position <= itemCount + 1){
            DoubleNode<T> newNode = new DoubleNode<>(entry);
            if (position == 1){
                newNode.setNext(head);
                head = newNode;
                updateFinger(head, 1);
            } else {
                DoubleNode<T> temp = getNodeAt(position - 1);
                updateFinger(temp, position - 1);
                newNode.setNext(temp.getNext());
                newNode.setPrev(temp);
                temp.setNext(newNode);

                if(newNode.getNext() != null)
                    newNode.getNext().setPrev(newNode);
            }
            itemCount++;
            return true;
        }
        return false;
    }

    /**
     * Removes an item from a specified position in the list.
     * 
     * @param position The position from where the item is to be removed.
     */
    public void remove(int position){
        if (position >= 1 && position <= itemCount) {
            if (position == 1) {
                head = head.getNext();
                updateFinger(head, 1);
            }
            else {
                DoubleNode<T> prev = getNodeAt(position - 1);
                updateFinger(prev, position - 1);
                DoubleNode<T> remove = prev.getNext();
                DoubleNode<T> curr = remove.getNext();
                prev.setNext(curr);
            }
            itemCount--;
        }
    }

    /**
     * Clears the list, setting it to an empty state.
     */
    public void clear(){
        head = null;
        itemCount = 0;
        finger = new Finger<>(1, null);
    }

    /**
     * Retrieves an item from a specified position in the list.
     * 
     * @param position The position of the item to be retrieved.
     * @return The item at the specified position.
     */
    public T getEntry(int position){
        return getNodeAt(position).getItem();
    }

    /**
     * Replaces the item at a specified position with a new entry.
     * 
     * @param position The position of the item to be replaced.
     * @param entry The new item to be placed at the specified position.
     * @return The original item that was replaced.
     */
    public T replace(int position, T entry){
        if (position >= 1 && position <= getLength()) {
            T toReturn;
            DoubleNode<T> val = getNodeAt(position);
            updateFinger(val, position);
            toReturn = val.getItem();
            val.setItem(entry);
            return toReturn;
        }
        return null;
    }

    /**
     * Gets the index of a specified item in the list.
     * 
     * @param key The item to find the index of.
     * @return The index of the item, or -1 if not found.
     */
    public int getIndexOf(T key){
        DoubleNode<T> val = head;
        for (int i = 1; i <= getLength() && val != null; i++) {
            if (val.getItem() == key)
                return i;
            val = val.getNext();

        }
        return -1;
    }

    /**
     * Converts the list to an array.
     * 
     * @return An array containing all the items in the list.
     */
    public Object[] toArray(){
        Object[] arr = new Object[getLength()];
        DoubleNode<T> curr = head;
        for (int i = 1; i <= getLength(); i++){
            arr[i - 1] = curr.getItem();
            curr = curr.getNext();
        }
        return arr;
    }

    /**
     * Retrieves the node at a specified position in the list.
     * 
     * @param position The position of the node to retrieve.
     * @return The node at the specified position.
     */
    private DoubleNode<T> getNodeAt(int position) {
        DoubleNode<T> curr = head;
        for (int i = 1; i < position; i++) {
            curr = head.getNext();
        }
        return curr;
    }

    /**
     * Provides a string representation of the list.
     * 
     * @return A string showing all items in the list.
     */
    @Override
    public String toString() {
        String s = "";
        Object[] data = toArray();
        for (int i = 0; i < data.length; i++){
            s += "[" + (i + 1) + "] " + data[i] + "\n";
        }
        return s;
    }

    /**
     * Updates the finger to point to a specified node and position.
     * 
     * @param curr The node to which the finger should point.
     * @param position The position of this node in the list.
     */
    private void updateFinger(DoubleNode<T> curr, int position){
        finger.setNode(curr);
        finger.setIdx(position);
    }

    /**
     * Gets the finger closest to a specified index.
     * 
     * @param idx The index to find the closest finger.
     * @return The closest finger to the specified index.
     */
    private Finger<T> getClosest(int idx){
        return null; // Placeholder for a method to find the closest finger (not implemented).
    }
}
