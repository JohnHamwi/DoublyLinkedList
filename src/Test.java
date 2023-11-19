import list.DoubleLinkedList;

public class Test {
    public static void main(String[] args) {
        // Create a new instance of DoubleLinkedList to store integers
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

        // Insert elements into the list at specified positions and print the operation's success status
        System.out.println(list.insert(1, 10));  // Inserts 10 at position 1, prints true if successful
        System.out.println(list.insert(2, 20));  // Inserts 20 at position 2
        System.out.println(list.insert(3, 30));  // Inserts 30 at position 3

        // Inserting more elements into the list at specified positions
        list.insert(2, 100);  // Inserts 100 at position 2, shifting the existing elements
        list.insert(1, 200);  // Inserts 200 at position 1

        // Removing the element at position 1
        list.remove(1);  // This will remove the element (200) that was inserted at position 1

        // Printing the current state of the list
        System.out.println(list);  // The list should now contain 100, 20, 10, and 30 in this order
    }
}
